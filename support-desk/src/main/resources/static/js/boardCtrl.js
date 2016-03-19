boardHome.kanbanBoardApp.controller('boardCtrl', function ($scope, $filter, boardService) {
	
    $scope.mapOfColWithTasks = [];
    $scope.columns = [];
    $scope.tasks = [];
    $scope.isLoading = false;
    $scope.colAddToggle=true;
    $scope.taskAddToggle=true;

    /**
     * Reinitialize form values
     */
    $scope.reset = function() {
      $scope.taskAddToggle='!taskAddToggle';
      $scope.colAddToggle='!colAddToggle';
    };

    function init() {
        $scope.refreshBoard();
        if(isEmpty($scope.columns)) {
        	generateColumns();
        }       
    };
    
    $scope.refreshBoard = function refreshBoard() {        
        $scope.isLoading = true;
        boardService.getColumns()
           .then(function (data) { 
        	   $scope.columns = data;
        	   for(col in $scope.columns) {
        		   alert(col.id + " - " + col.boardColumnName);
        	   }
               $scope.isLoading = false;
           }, onError);
        $scope.isLoading = true;
        boardService.getTasks()
        .then(function (data) {   
        	$scope.tasks = data;
            $scope.isLoading = false;
        }, onError);
        
        $scope.mapOfColWithTasks = [];
    	for(c in $scope.columns) {
    		for(t in $scope.tasks) {
    			if(c.id === t.boardColumnId) {
	        		if($scope.mapOfColWithTasks[c.id] === undefined) {
	        			$scope.mapOfColWithTasks.push({'colId':c.id, 'tasks':{t}});
	        		} else {
	        			var tasks = $filter('filter')($scope.mapOfColWithTasks, {'colId': colId})[0];
	        			tasks.push(t);
	        		}
    			}
        	}
        }
    };
    
    var generateColumns = function generateColumns() {
    	//var tasks = [];
    	var i = 0, till = 3;
    	var c = { boardColumnDescription:"to do column", boardColumnName:"to do"};
    	$scope.columns.push(c);
        boardService.addColumn(c);
    	c = { boardColumnDescription:"in progress column", boardColumnName:"in progress"};
    	$scope.columns.push(c);
        boardService.addColumn(c);
    	c = { boardColumnDescription:"done column", boardColumnName:"done"};
    	$scope.columns.push(c);
        boardService.addColumn(c);
        /*for(c in $scope.columns) {
     	   till += i;
     	   while(i <= till) {
     		   ++i;
         	   tasks.push({ id:i, taskName:"Task " + i, taskDescription:"Task " + i + " Description" });
            }
     	    boardService.addTask(tasks);
     	    $scope.map.push({col:c, tasks:tasks});
        }*/
        return $scope.columns;
    }
    
    // add a column
    $scope.addColumn = function addColumn(colName,colDescription) {
    	if(isEmpty(colName) || isEmpty(colDescription)) {
			alert("Insufficient Data! Please provide values for column name and description!");
		} else {
			$scope.isLoading = true;
	    	var col = { boardColumnDescription:colDescription, boardColumnName:colName };
	    	//$scope.columns[$scope.columns.length] = col;
	    	boardService.addColumn(col)
	    		.then(function (columnAdded) {
                    $scope.isLoading = false; 
                    alert("Column added");
                }, onError);
	        $scope.reset();
	    	$scope.refreshBoard();
		}
    };
    
    //add a new task
	$scope.addTask = function addTask(colId,taskName,taskDescription) {
		if(isEmpty(colId) || isEmpty(taskName) || isEmpty(taskDescription)) {
			alert("Insufficient Data! Please provide values for task name and description!");
		} else {
			$scope.isLoading = true;
			var task = { taskName:taskName, taskDescription:taskDescription, boardColumnId:colId };
			boardService.addTask(task)
				.then(function (taskeAdded) {
                    $scope.isLoading = false; 
                    alert("Task added");
                }, onError);
            /*$scope.isLoading = true;
			boardService.updateColumn(col)
			.then(function (colUpdated) {
                $scope.isLoading = false; 
                alert("Column Updated Too");
            }, onError);*/
	        $scope.reset();
            $scope.refreshBoard();
		}
	};
	
	//add a new task
	$scope.editTask = function editTask(colId, taskId) {
		$scope.isLoading = true;
		alert(colId + " - " + taskId);
		var col = $filter('filter')($scope.columns, {'id': colId})[0];
		var task = $filter('filter')(col.tasks, {'id': taskId})[0];
		alert(col.id + " - " + task.id);
		boardService.updateTask(task)
			.then(function (taskUpdated) {
                $scope.isLoading = false; 
                alert("Task updated");
            }, onError);
            $scope.refreshBoard();
	};
	
    $scope.onDrop = function (data, sourceColId, targetColId) {        
        boardService.canMoveTask(sourceColId, targetColId)
            .then(function (canMove) {                
                if (canMove == "true") {                 
                    boardService.moveTask(data.id, targetColId).then(function (taskMoved) {
                        $scope.isLoading = false;    
                    }, onError);
                    $scope.isLoading = true;
                }
            }, onError);
        $scope.refreshBoard();
    };

    var onError = function (errorMessage) {
        $scope.isLoading = false;
    };
    
    // Speed up calls to hasOwnProperty
    var hasOwnProperty = Object.prototype.hasOwnProperty;

    function isEmpty(obj) {

        // null and undefined are "empty"
        if (obj == null) return true;

        // Assume if it has a length property with a non-zero value
        // that that property is correct.
        if (obj.length > 0)    return false;
        if (obj.length === 0)  return true;

        // Otherwise, does it have any properties of its own?
        // Note that this doesn't handle
        // toString and valueOf enumeration bugs in IE < 9
        for (var key in obj) {
            if (hasOwnProperty.call(obj, key)) return false;
        }

        return true;
    }

    init();
    
});