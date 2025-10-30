// @Author Matheus N.

public abstract class UIBaseController
{
    public SpatialObject my_object;
    public ObjectFile button_element;
    public int selected_slot = -1;
    public int last_selected = -1;
    public int slots_amount = 4;
    public int slots_max = 4;
    public AList<SpatialObject> slots = new AList();
    public String key_type;
    public Color pressed_color = new Color();
    public Color normal_color = new Color();
    public Item[] slots_data;
    
    void instantiate_slots(int colun)
    {
        for(int amount = 0; amount < slots_amount; amount++)
        {
            SpatialObject slot = my_object.instantiateHasChild(button_element);
            SUIKeyEventListener key = slot.findComponentInChildren(SUIKeyEventListener.class);
            key.setKeyName(key_type + (amount + (colun * slots_amount)));
            
            slots.add(slot);
            set_visible_amount(false, amount);
        }
    }
    
    void set_visible_amount(boolean visible, int id)
    {
        SUIText text_ui = slots.get(id).getChildAt(0).findComponent(SUIText.class);
        text_ui.setEnabled(visible);
    }
    
    void set_button_color(int id, Color color)
    {
        SUIButton button_ui = slots.get(id).getChildAt(0).findComponent(SUIButton.class);
        button_ui.setNormalColor(color);
    }
    
    void set_border(int id, boolean active)
    {
        SUIImage border_ui = slots.get(id).findComponent(SUIImage.class);
        border_ui.setEnabled(active);
    }
    
    boolean has_item(int slot)
    {
        if(slot < 0)
            return false;
    
        return slots_data[slot] != null;
    }
    
    Item get_item(int slot)
    {
        return slots_data[slot];
    }
    
    void remove_item(int slot)
    {
        slots_data[slot] = null;
    
        set_button_color(slot, normal_color);
        update_item_amount(slot, 0, false);
        set_button_icon(slot, null, false);
    }
    
    void move_item(Item item, int from, int to)  
    {
        if(!has_item(to))
        {
            add_item(item, to);
            remove_item(from);
            
            return;
        }
        
        Item other = get_item(to);
        
        remove_item(to);
        add_item(item, to);
        
        remove_item(from);
        add_item(other, from);
    }
    
    void add_item(Item item, int slot)
    {
        if(slot >= slots_amount)
            return;
    
        if(slots_data[slot] == null)
        {
            slots_data[slot] = item;
        }
   
        set_button_color(slot, item.background_color);
        update_item_amount(slot, item.amount, true);
        set_button_icon(slot, item.icon, true);
    }
    
    void update_item_amount(int slot, int amount, boolean visible_text)
    {
        set_visible_amount(visible_text, slot);
    
        SUIText text_ui = slots.get(slot).getChildAt(0).findComponent(SUIText.class);
        text_ui.setText(amount);
    }
    
    void set_button_icon(int id, Texture icon, boolean visible_icon)
    {
        SUIImage button_ui = slots.get(id).getChildAt(1).findComponent(SUIImage.class);
        button_ui.setImage(icon);
    }

    void unselect()
    {
        if(has_item(selected_slot))
        {
            set_button_color(selected_slot, slots_data[selected_slot].background_color);
            set_border(selected_slot, false);
        }
        else
        {
            set_button_color(selected_slot, normal_color);
            set_border(selected_slot, false);
        }
    
        selected_slot = -1;  
    }   

    void select_slot(int id)
    {
        if(id == selected_slot)
        {
            last_selected = id;
            return;
        }
        
        if(selected_slot >= 0)
        {
            set_button_color(selected_slot, normal_color);
            set_border(selected_slot, false);
        }
        
        if(id >= 0)
        {
            set_button_color(id, pressed_color);
            set_border(id, true);
        }
        
        last_selected = selected_slot;
        selected_slot = id;
    }
}
  
