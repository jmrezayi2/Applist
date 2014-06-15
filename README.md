Usage:
        
        var success = function(message) { alert(message); };
        var error = function(message) { alert("Oopsie! " + message); };
        Applist.createEvent('', '', '', '', '', success, error)