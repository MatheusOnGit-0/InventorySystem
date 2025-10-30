// @Author Matheus Nunes

class SettingsJson
{
    public int slot_size = 150;
    public int hotbar_size = 4;
    public int inventory_size = 8;
}

public class SettingsData
{
    private SettingsJson settings;
    
    public SettingsData()
    {
        settings = load();
        
        if(settings == null)
            settings = new SettingsJson();
    }
    
    public SettingsJson get_settings()
    {
        return settings;
    }
    
    public void save()
    {
        String json = Json.toJson(get_settings());
        File settings_file = new File(Directories.internal() + "InventoryPluginCfg.json");
        
        try
        {
            FileLoader.exportTextToFile(json, settings_file);
        } catch(Exception e)
        {
            Console.log(e);
        }
        
        apply_settings();
    }
    
    public void apply_settings()
    {
        Thread.runOnEngine(new Runnable()
        {
            public void run()
            {
                SpatialObject ui_interface = WorldController.findObject("Interface");
        
                if(ui_interface == null)
                    return;
                
                ui_interface.callFunction("update_hotbar_size", get_hotbar_size(), get_slot_size());
                ui_interface.callFunction("update_inventory_size", get_inventory_size(), get_slot_size());
            }
         
        });
    }
    
    public SettingsJson load()
    {
        String json = null;
        File settings_file = new File(Directories.internal() + "InventoryPluginCfg.json");
        
        if(!settings_file.canRead())
            return null;
        
        try
        {
           json = FileLoader.loadTextFromFile(settings_file);
        } catch(Exception e)
        {
            Console.log(e);
        }
        
        if(json == null)
            return null;
            
        return (SettingsJson) Json.fromJson(json, SettingsJson.class);
    }
    
    public int get_slot_size()
    {
        return get_settings().slot_size;
    }
    
    public int get_hotbar_size()
    {
        return get_settings().hotbar_size;
    }
    
    public int get_inventory_size()
    {
        return get_settings().inventory_size;
    }
    
    public void set_slot_size(int size)
    {
        get_settings().slot_size = size;
    }
    
    public void set_hotbar_size(int size)
    {
        get_settings().hotbar_size = size;
    }
    
    public void set_inventory_size(int size)
    {
        get_settings().inventory_size = size;
    }
}
