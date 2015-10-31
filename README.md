#A plugin to give the list of all installed apps with their icons


##Add the plugin using CLI:

**cordova plugin add https://github.com/jmrezayi2/Applist**

or 

**cordova plugin add cordova-plugin-applist2**

##Usage:
        
        var success = function(app_list) { alert(JSON.stringify((app_list)); };
        var error = function(app_list) { alert("Oopsie! " + app_list); };
        Applist.createEvent('', '', '', '', '', success, error)
        
##Detailed usage:        
Gives the list of all apps installed on the phone in a JSON object and also saves an icon of each of them in sdcardd//com.ionicframework.xxx/Cache/ ;        

JSONObject info;
app_list.info.name is app name 
app_list.info.img is app-logo cache in storage--sdcard0 /com.ionicframework.xxx/Cache/ '.png'.


##To Do:

**Add the path to save as an argument in the plugin**

**Add iOS support**

##Support

If you can, please fork me. You can also [donate to us](https://www.paypal.com/ca/cgi-bin/webscr?cmd=_flow&SESSION=xT0cic-TDI4_xvjvPzqbG_KIS3bMJtR2yhoXlDKmr90wnVgHMrjyvQ1Z7nS&dispatch=5885d80a13c0db1f8e263663d3faee8d66f31424b43e9a70645c907a6cbd8fb4) any amount.
