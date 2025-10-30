// @Author Matheus Nunes

import android.view.*;
import android.widget.*;
import android.graphics.Color;

public class ConfigPanel extends ViewPanel
{
   
    public SettingsData settings;
    public ConfigLayout layout;
    
    public AEditText slot_size_input;
    public AEditText inventory_size_input;
    public AEditText hotbar_size_input;
    public ALinearLayout content_layout;
    public AButton apply_button;
    public ALinearLayout inventory_panel;
    public ALinearLayout hotbar_panel;
    
    public ConfigPanel()
    {
     
    }
    
    @Override
    public View onAttach()
    {
        settings = new SettingsData();
        layout = new ConfigLayout();
        
        ALinearLayout root = layout.root_layout();
        ALinearLayout side_menu = layout.side_menu();
        
        AButton inventory_button = layout.button("Inventory");
        inventory_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                show_inventory_settings();
            }
        });

        AButton hotbar_button = layout.button("Hotbar");
        hotbar_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                show_hotbar_settings();
            }
        });
        
        content_layout = layout.content_layout();
        
        inventory_panel = layout.inventory_panel(this, settings.get_slot_size(), settings.get_inventory_size());
        hotbar_panel = layout.hotbar_panel(this, settings.get_slot_size(), settings.get_hotbar_size());
        
        side_menu.addView(inventory_button);
        side_menu.addView(hotbar_button);
        
        apply_button = layout.confirm_button();
        apply_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                apply_settings();
            }
        });
        
        root.addView(side_menu);
        root.addView(content_layout);
        
        show_inventory_settings();
        
        return root;
    }
    
    private void apply_settings()
    {
        settings.set_slot_size(get_slot_size());
        settings.set_hotbar_size(get_hotbar_size());
        settings.set_inventory_size(get_inventory_size());
        
        settings.save();
        
        Toast.showText("Configurações aplicadas", Toast.LENGTH_SHORT);
    }
    
    private void show_inventory_settings()
    {
        content_layout.removeAllViews();
        content_layout.addView(inventory_panel);
        content_layout.addView(apply_button);
    }

    private void show_hotbar_settings()
    {
        content_layout.removeAllViews();
        content_layout.addView(hotbar_panel);
        content_layout.addView(apply_button);
    }
    
    public int get_slot_size()
    {
        try
        {
            return Integer.parseInt(slot_size_input.getText().toString());
        } catch (Exception e)
        {
            return settings.get_slot_size();
        }
    }

    public int get_inventory_size()
    {
        try
        {
            return Integer.parseInt(inventory_size_input.getText().toString());
        } catch (Exception e)
        {
            return settings.get_inventory_size();
        }
    }

    public int get_hotbar_size()
    {
        try
        {
            return Integer.parseInt(hotbar_size_input.getText().toString());
        } catch (Exception e)
        {
            return settings.get_hotbar_size(); 
        }
    }
}
