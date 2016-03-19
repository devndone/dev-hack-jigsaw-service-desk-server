boardHome.kanbanBoardApp.service('boardService', function ($http) {

	$http.defaults.headers.post["Content-Type"] = "application/json";
	$http.defaults.headers.put["Content-Type"] = "application/json";
	
    var getColumns = function () {
        return $http.get("/boards/search/findByBoardColumnArchived?archivedfalse=0").then(function (response) {
        	//$http.get("/boards").then(function (response) {
        		return response.data;
        });
    };
    
    //add a new Column
	var addColumn = function addColumn(col) {
		return $http.post('/boards', col).then(function(response) {
        	 	return response.status == 201;//200;
		    });
	};

    //edit a Column
	var updateColumn = function updateColumn(col) {
		return $http.put("/boards/"+col.id, col).then(function(response) {
        	 	return response.status == 201;//200;
		    });
	};
	
	var getTasks = function () {
        return $http.get("/tasks/search/findByTaskArchived?archivedfalse=0").then(function (response) {
        	//$http.get("/tasks").then(function (response) {
        		response.data;
        });
    };
	
    //add a new task
	var addTask = function addTask(task) {
		return $http.post('/tasks', task).then(function(response) {
        	 	return response.status == 201;//200;
		    });
	};

    //edit a task
	var updateTask = function updateTask(task) {
		return $http.put("/tasks/"+task.id, {
			 id: task.id,
             taskName: task.taskName,
             taskDescription: task.taskDescription
         }).then(function(response) {
        	 	return response.status == 201;//200;
		    });
	};
	
    var canMoveTask = function (sourceColIdVal, targetColIdVal) {
        return $http.get("/CanMove/"+sourceColIdVal+"/"+targetColIdVal+"")//, { params: { sourceColId: sourceColIdVal, targetColId: targetColIdVal } })
            .then(function (response) {
                return response.data;
            });
    };

    var moveTask = function (taskIdVal, targetColIdVal) {
        return $http.post("/MoveTask/"+taskIdVal+"/"+targetColIdVal+"")//, { taskId: taskIdVal, targetColId: targetColIdVal })
            .then(function (response) {
                return response.status == 200;
            });
    };
    
    return {
        getColumns: getColumns,
        addColumn: addColumn,
        updateColumn: updateColumn,
        getTasks: getTasks,
        addTask: addTask,
        updateTask: updateTask,
        canMoveTask: canMoveTask,
        moveTask: moveTask
    };
});