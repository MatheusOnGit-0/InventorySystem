import android.view.*;
import android.widget.*;
import android.graphics.Color;

// @Author Matheus Nunes
public class ConfigLayout
{
    private final String color_root_bg = "#1E1E1E";
    private final String color_side_bg = "#2B2B2B";
    private final String color_content_bg = "#252525";
    private final String color_field_bg = "#2B2B2B";
    private final String color_label = "#CCCCCC";
    private final String color_title = "#FFFFFF";
    
    private final int side_width = 250;
    private final int root_padding = 0;
    private final int side_padding = 0;
    private final int content_padding = 0;
    private final int field_padding = 0;
    private final int field_input_width = 150;
    private final int field_text_size = 12;
    private final int title_text_size = 14;
    private final int apply_margin_top = 10;
    
    public ALinearLayout root_layout()
    {
        ALinearLayout root = new ALinearLayout();
        root.setOrientation(LinearLayout.HORIZONTAL);
        root.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        root.setPadding(root_padding, root_padding, root_padding, root_padding);
        root.setBackgroundColor(Color.parseColor(color_root_bg));
        
        return root;
    }
    
    public ALinearLayout content_layout()
    {
        ALinearLayout content_layout = new ALinearLayout();
        content_layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams content_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        content_params.setMargins(root_padding, 0, 0, 0);
        content_layout.setLayoutParams(content_params);
        content_layout.setPadding(content_padding, content_padding, content_padding, content_padding);
        content_layout.setBackgroundColor(Color.parseColor(color_content_bg));
        
        return content_layout;
    }
    
    public ALinearLayout side_menu()
    {
        ALinearLayout side_menu = new ALinearLayout();
        side_menu.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams side_params = new LinearLayout.LayoutParams(side_width, ViewGroup.LayoutParams.MATCH_PARENT);
        side_menu.setLayoutParams(side_params);
        side_menu.setPadding(side_padding, side_padding, side_padding, side_padding);
        side_menu.setBackgroundColor(Color.parseColor(color_side_bg));
        
        return side_menu;
    }
    
    public AEditText input(String hint, String default_value) {
        AEditText edit_text = new AEditText();
        edit_text.setText(default_value);
        edit_text.setHint(hint);
        edit_text.setTextSize(field_text_size);
        edit_text.setTextColor(Color.parseColor(color_title));
        
        return edit_text;
    }
    
    public AButton button(String text)
    {
        AButton button = new AButton();
        button.setText(text);
        button.setAllCaps(false);
        button.setTextSize(field_text_size);
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        
        return button;
    }
    
    public AButton confirm_button()
    {
        AButton confirm_button = new AButton();
        confirm_button.setText("Apply Changes");
        confirm_button.setAllCaps(false);
        confirm_button.setTextSize(field_text_size);
        LinearLayout.LayoutParams confirm_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        confirm_params.setMargins(0, apply_margin_top, 0, 0);
        confirm_button.setLayoutParams(confirm_params);
        
        return confirm_button;
    }
    
    private ATextView title(String text, int x, int y, int z, int w) {
        ATextView title = new ATextView();
        title.setText(text);
        title.setTextSize(title_text_size);
        title.setTextColor(Color.parseColor(color_title));
        title.setPadding(x, y, z, w);
        
        return title;
    }
    
    public ALinearLayout inventory_panel(ConfigPanel main, int inventory_size, int inventory_count)
    {
        ALinearLayout panel = new ALinearLayout();
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(field_padding, field_padding, field_padding, field_padding);

        panel.addView(title("INVENTORY", 0, 0, 0, 8));

        main.inventory_size_input = input("Slot Count", String.valueOf(inventory_count));
        main.slot_size_input = input("Slot Size", String.valueOf(inventory_size));

        panel.addView(create_field_row("Slot Size", main.slot_size_input));
        panel.addView(create_field_row("Slot Count", main.inventory_size_input));

        return panel;
    }
    
    public ALinearLayout hotbar_panel(ConfigPanel main, int hotbar_size, int hotbar_count)
    {
        ALinearLayout panel = new ALinearLayout();
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(field_padding, field_padding, field_padding, field_padding);

        panel.addView(title("HOTBAR", 0, 0, 0, 8));
        
        main.hotbar_size_input = input("Slot Count", String.valueOf(hotbar_count));
        main.slot_size_input = input("Slot Size", String.valueOf(hotbar_size));

        panel.addView(create_field_row("Slot Size", main.slot_size_input));
        panel.addView(create_field_row("Slot Count", main.hotbar_size_input));

        return panel;
    }
    
    public LinearLayout create_field_row(String label_text, AEditText input) {
        ALinearLayout row = new ALinearLayout();
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setPadding(field_padding, field_padding, field_padding, field_padding);
        row.setBackgroundColor(Color.parseColor(color_field_bg));

        ATextView label = new ATextView();
        label.setText(label_text);
        label.setTextSize(field_text_size);
        label.setTextColor(Color.parseColor(color_label));
        LinearLayout.LayoutParams lbl_params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        label.setLayoutParams(lbl_params);

        LinearLayout.LayoutParams input_params = new LinearLayout.LayoutParams(field_input_width, ViewGroup.LayoutParams.WRAP_CONTENT);
        input.setLayoutParams(input_params);

        row.addView(label);
        row.addView(input);

        LinearLayout.LayoutParams row_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row_lp.setMargins(0, field_padding, 0, field_padding);
        row.setLayoutParams(row_lp);

        return row;
    }
}
