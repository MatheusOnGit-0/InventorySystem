// @Author Matheus N.

public class InventoryUI extends UIBaseController
{
    public String key_type = "inventory_key";
    public ObjectFile colun_element;
    public boolean is_open;
    
    public InventoryUI()
    {
        super.key_type = this.key_type;
    }
    
    void create()
    {
        super.slots_data = new Item[slots_amount];
        create_slots();
    }
    
    void instatiate_colun_buttons(SpatialObject colun, int offset, int slots_amount)
    {
        for(int amount = 0; amount < slots_amount; amount++)
        {
            SpatialObject button = colun.instantiateHasChild(button_element);
            SUIKeyEventListener key = button.findComponentInChildren(SUIKeyEventListener.class);
            key.setKeyName(key_type + (amount + (offset * slots_amount)));
        
            slots.add(button);
            set_visible_amount(false, amount + (offset * slots_amount));
        }
}

    
    void create_slots()
    {
        for(int i = 0; i < slots_amount / slots_max; i++)
        {
            SpatialObject colun = my_object.instantiateHasChild(colun_element);
            instatiate_colun_buttons(colun, i, slots_max);
        }
    }
}
