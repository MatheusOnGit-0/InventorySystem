// @Author Matheus N.

public class HotbarUI extends UIBaseController
{
    public String key_type = "hotbar_key";
    
    public HotbarUI()
    {
        super.key_type = this.key_type;
        super.slots_data = new Item[slots_amount];
    }
}
