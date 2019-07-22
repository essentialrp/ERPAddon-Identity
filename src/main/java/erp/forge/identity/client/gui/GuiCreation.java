package erp.forge.identity.client.gui;

import erp.forge.identity.ERPIdentityForge;
import erp.forge.identity.network.message.MessageCreation;
import net.voxelindustry.brokkgui.element.GuiLabel;
import net.voxelindustry.brokkgui.element.ToastManager;
import net.voxelindustry.brokkgui.element.input.GuiButton;
import net.voxelindustry.brokkgui.element.input.GuiRadioButton;
import net.voxelindustry.brokkgui.element.input.GuiTextfield;
import net.voxelindustry.brokkgui.element.input.GuiToggleGroup;
import net.voxelindustry.brokkgui.gui.BrokkGuiScreen;
import net.voxelindustry.brokkgui.paint.Texture;
import net.voxelindustry.brokkgui.panel.GuiRelativePane;
import net.voxelindustry.brokkgui.shape.Rectangle;

public class GuiCreation extends BrokkGuiScreen
{
    public ToastManager toastManager;

    public GuiCreation()
    {
        super(0.5f, 0.5f, 200, 200);

        this.addStylesheet("/assets/erp-identity-forge/gui/css/demo.css");

        final GuiRelativePane pane = new GuiRelativePane();
        this.setMainPanel(pane);

        pane.setBackgroundTexture(new Texture("erp-identity-forge:textures/gui/background.png"));


        //FIRST NAME
        GuiLabel label = new GuiLabel();
        label.setText("First Name");
        label.setTranslate(36, 15);
        pane.addChild(label, 0, 0);


        GuiTextfield field = new GuiTextfield();
        field.setHeight(15);
        field.setWidth(80);
        field.setTranslate(50, 30);
        field.setStyle("border-color: black; border-width: 1;");
        pane.addChild(field, 0, 0);

        //FIRST NAME
        GuiLabel label2 = new GuiLabel();
        label2.setText("Last Name");
        label2.setTranslate(36, 45);
        pane.addChild(label2, 0, 0);


        GuiTextfield field2 = new GuiTextfield();
        field2.setHeight(15);
        field2.setWidth(80);
        field2.setTranslate(50, 60);
        field2.setStyle("border-color: black; border-width: 1;");
        pane.addChild(field2, 0, 0);


        //SEXE
        Rectangle icon = new Rectangle(8, 8);
        icon.setStyle("color: red;");

        GuiRadioButton homme = new GuiRadioButton("Male");
        homme.getLabel().setIcon(icon);
        homme.setHeight(10);
        homme.setTranslate(34, 95);
        homme.setSelected(true);
        pane.addChild(homme, 0,0);

        GuiRadioButton femme = new GuiRadioButton("Female");
        femme.getLabel().setIcon(icon);
        femme.setHeight(10);
        femme.setTranslate(40, 80);
        pane.addChild(femme, 0,0);

        final GuiToggleGroup toggleGroup = new GuiToggleGroup();
        toggleGroup.setAllowNothing(true);
        toggleGroup.addButtons(homme, femme);

        //AGE
        GuiLabel age = new GuiLabel();
        age.setText("Age");
        age.setTranslate(20, 112);
        pane.addChild(age, 0, 0);


        GuiTextfield field3 = new GuiTextfield();
        field3.setHeight(15);
        field3.setWidth(20);
        field3.setTranslate(20, 127);
        field3.setStyle("border-color: black; border-width: 1;");
        pane.addChild(field3, 0, 0);

        //OTHER
        final GuiButton button = new GuiButton("Submit");

        button.setWidth(10);
        button.setHeight(30);
        button.setTranslate(170, 180);
        button.setStyle("background-color: green; border-color: green; border-width: 2; text-color: khaki");
        button.setOnClickEvent(e ->
                ERPIdentityForge.instance.getPacketChannel().sendToServer(new MessageCreation(field.getText(), field2.getText(), homme.isSelected(), field3.getText())));
        pane.addChild(button, 0, 0);

    }

    @Override
    public void initGui()
    {
        super.initGui();
    }
}
