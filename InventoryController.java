// @Author Matheus N.

public SpatialObject hotbar_object;
public SpatialObject inventory_object;
public ObjectFile button_element, colun_element;
public Color pressed_color = new Color();
public Color normal_color = new Color();
public int inventory_break_max = 4;
private HotbarUI hotbar;
private InventoryUI inventory;
private SettingsData settings;

void start()
{
    settings = new SettingsData();
    
    create_hotbar();
    create_inventory();
}

void repeat()
{
    hotbar_controller();
    inventory_controller();
}

void add_hotbar_item(Item item, int slot)
{
    hotbar.add_item(item, slot);
}

void hotbar_controller()
{
    if(hotbar == null) 
        return;
    
    for(int slot_id = 0; slot_id < hotbar.slots_amount; slot_id++)
    {   
        if(Input.isKeyDown(hotbar.key_type + slot_id))
        {
            hotbar.select_slot(slot_id);
            int last_slot = hotbar.last_selected;
            
            if(inventory.is_open)
            {
                if(hotbar.last_selected == slot_id)
                {
                    hotbar.unselect();
                    return;
                }
            
                if(hotbar.has_item(last_slot))
                {
                    hotbar.move_item(hotbar.get_item(last_slot), last_slot, slot_id);
                }
                
                if(inventory.selected_slot >= 0)
                {
                    if(inventory.has_item(inventory.selected_slot))
                    {
                        hotbar.add_item(inventory.get_item(inventory.selected_slot), slot_id);
                        inventory.remove_item(inventory.selected_slot);
                        inventory.unselect();
                    }
                }
            }
        }
    }
}

void inventory_controller()
{
    if(inventory == null)
        return;
    
    if(Input.isKeyDown("inventory"))
    {
        inventory.is_open = !inventory.is_open;
        inventory_object.setEnabled(inventory.is_open);
    }
    
    for(int slot_id = 0; slot_id < inventory.slots_amount; slot_id++)
    {   
        if(Input.isKeyDown(inventory.key_type + slot_id))
        {
            inventory.select_slot(slot_id);
            int last_slot = inventory.last_selected;
            
            if(inventory.is_open)
            {
                if(inventory.last_selected == slot_id)
                {
                    inventory.unselect();
                    return;
                }
            
                if(inventory.has_item(last_slot))
                {
                    inventory.move_item(inventory.get_item(last_slot), last_slot, slot_id);
                }
                
                if(hotbar.selected_slot >= 0)
                {
                    if(hotbar.has_item(hotbar.selected_slot))
                    {
                        inventory.add_item(hotbar.get_item(hotbar.selected_slot), slot_id);
                        hotbar.remove_item(hotbar.selected_slot);
                        hotbar.unselect();
                    }
                }
            }
        }
    }
}

Item create_item(Color color, int amount)
{
    Item item = new Item();
    item.background_color = color;
    item.amount = amount;
    
    return item;
}

void create_hotbar()
{
    hotbar = new HotbarUI();
    hotbar.my_object = hotbar_object;
    hotbar.button_element = button_element;
    hotbar.pressed_color = pressed_color;
    hotbar.normal_color = normal_color;
    hotbar.slots_amount = settings.get_hotbar_size();
    
    hotbar.instantiate_slots(0);
}

void create_inventory()
{
    inventory = new InventoryUI();
    inventory.my_object = inventory_object;
    inventory.button_element = button_element;
    inventory.pressed_color = pressed_color;
    inventory.normal_color = normal_color;
    inventory.colun_element = colun_element;
    inventory.slots_amount = settings.get_inventory_size();
    inventory.is_open = true;
    
    inventory.create();
}

void update_hotbar_size(int size, int slot_size)
{
    int total_width = size * slot_size;
    SUIRect rect = (SUIRect) hotbar_object.findComponent(SUIRect.class);
    
    rect.setWidth(total_width);
    rect.setHeight(slot_size);
}

void update_inventory_size(int size, int slot_size)
{
    int coluns = (int) size / inventory_break_max;
    int total_width = (size / coluns) * slot_size;
    int total_height = coluns * slot_size;
    
    SUIRect rect = (SUIRect) inventory_object.findComponent(SUIRect.class);
    
    rect.setWidth(total_width);
    rect.setHeight(total_height);
}
