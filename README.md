Add the plugin using CLI:

cordova plugin add https://github.com/jmrezayi2/Applist


Usage:
        
        var success = function(message) { alert(message); };
        var error = function(message) { alert("Oopsie! " + message); };
        Applist.createEvent('', '', '', '', '', success, error)
        
        
Gives the list of all apps installed on the phone seperated by ;        
