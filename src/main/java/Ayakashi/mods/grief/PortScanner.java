package Ayakashi.mods.grief;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class PortScanner extends GuiScreen {
    private final List<Integer> ports = new ArrayList<Integer>();
    private final GuiScreen before;
    private GuiTextField hostField;
    private GuiTextField minPortField;
    private GuiTextField maxPortField;
    private GuiTextField threadsField;
    private GuiButton buttonToggle;
    private boolean running;
    private String status = "";
    private String host;
    private int currentPort;
    private int maxPort;
    private int minPort;
    private int checkedPort;

    public PortScanner(GuiScreen before) {
        this.before = before;
    }

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.hostField = new GuiTextField(0, mc.fontRendererObj, width / 2 - 100, 40, 200, 20);
        this.hostField.setFocused(true);
        this.hostField.setMaxStringLength(Integer.MAX_VALUE);

        this.minPortField = new GuiTextField(1, mc.fontRendererObj, width / 2 - 100, 80, 90, 20);
        this.minPortField.setMaxStringLength(5);
        this.minPortField.setText(String.valueOf(25500));

        this.maxPortField = new GuiTextField(2, mc.fontRendererObj, width / 2 + 10, 80, 90, 20);
        this.maxPortField.setMaxStringLength(5);
        this.maxPortField.setText(String.valueOf(25600));

        this.threadsField = new GuiTextField(3, mc.fontRendererObj, width / 2 - 100, 120, 200, 20);
        this.threadsField.setMaxStringLength(Integer.MAX_VALUE);
        this.threadsField.setText(String.valueOf(500));

        this.buttonToggle = new GuiButton(1, width / 2 - 100, height / 4 + 115, this.running ? "Stop" : "Start");
        this.buttonList.add(this.buttonToggle);
        this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 140, "Back"));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        PortScanner.drawCenteredString(mc.fontRendererObj, "Server IP", width / 2, 30, 10526880);
        PortScanner.drawCenteredString(mc.fontRendererObj, "Start Port", width / 2 - 55, 70, 10526880);
        PortScanner.drawCenteredString(mc.fontRendererObj, "End Port", width / 2 + 55, 70, 10526880);
        PortScanner.drawCenteredString(mc.fontRendererObj, "Threads", width / 2, 110, 10526880);

        PortScanner.drawCenteredString(mc.fontRendererObj, this.running ? +this.checkedPort + " / " + this.maxPort : (this.status == null ? "" : this.status), width / 2, height / 4 + 95, 16777215);
        this.buttonToggle.displayString = this.running ? "§cStop" : "§aStart";
        this.hostField.drawTextBox();
        this.minPortField.drawTextBox();
        this.maxPortField.drawTextBox();
        this.threadsField.drawTextBox();
        PortScanner.drawString(mc.fontRendererObj, "§aOpen Ports:", 2, 2, Color.WHITE.hashCode());
        List<Integer> list = this.ports;
        synchronized (list) {
            int i = 12;
            for (Integer integer : this.ports) {
                PortScanner.drawString(mc.fontRendererObj, String.valueOf(integer), 2, i, Color.WHITE.hashCode());
                i += mc.fontRendererObj.FONT_HEIGHT;
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0: {
                mc.displayGuiScreen(before);
                break;
            }
            case 1: {
                if (this.running) {
                    this.running = false;
                } else {
                    int threads;
                    this.host = this.hostField.getText();
                    if (this.host.isEmpty()) {
                        this.status = "\u00a7cInvalid host";
                        return;
                    }
                    try {
                        this.minPort = Integer.parseInt(this.minPortField.getText());
                    } catch (NumberFormatException e) {
                        this.status = "\u00a7cInvalid min port";
                        return;
                    }
                    try {
                        this.maxPort = Integer.parseInt(this.maxPortField.getText());
                    } catch (NumberFormatException e) {
                        this.status = "\u00a7cInvalid max port";
                        return;
                    }
                    try {
                        threads = Integer.parseInt(this.threadsField.getText());
                    } catch (NumberFormatException e) {
                        this.status = "\u00a7cInvalid threads";
                        return;
                    }
                    this.ports.clear();
                    this.currentPort = this.minPort - 1;
                    this.checkedPort = this.minPort;
                    for (int i = 0; i < threads; ++i) {
                        new Thread(() -> {
                            try {
                                while (this.running && this.currentPort < this.maxPort) {
                                    int port = ++this.currentPort;
                                    try {
                                        Socket socket = new Socket();
                                        socket.connect(new InetSocketAddress(this.host, port), 500);
                                        socket.close();
                                        List<Integer> list = this.ports;
                                        synchronized (list) {
                                            if (!this.ports.contains(port)) {
                                                this.ports.add(port);
                                            }
                                        }
                                    } catch (Exception socket) {
                                        // empty catch block
                                    }
                                    if (this.checkedPort >= port) continue;
                                    this.checkedPort = port;
                                }
                                this.running = false;
                                this.buttonToggle.displayString = "Start";
                            } catch (Exception e) {
                                this.status = "\u00a7a\u00a7l" + e.getClass().getSimpleName() + ": \u00a7c" + e.getMessage();
                            }
                        }).start();
                    }
                    this.running = true;
                }
                this.buttonToggle.displayString = this.running ? "Stop" : "Start";
            }
        }
        super.actionPerformed(button);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (1 == keyCode) {
            mc.displayGuiScreen(before);
            return;
        }
        if (this.running) {
            return;
        }
        if (this.hostField.isFocused()) {
            this.hostField.textboxKeyTyped(typedChar, keyCode);
        }
        if (this.minPortField.isFocused() && !Character.isLetter(typedChar)) {
            this.minPortField.textboxKeyTyped(typedChar, keyCode);
        }
        if (this.maxPortField.isFocused() && !Character.isLetter(typedChar)) {
            this.maxPortField.textboxKeyTyped(typedChar, keyCode);
        }
        if (this.threadsField.isFocused() && !Character.isLetter(typedChar)) {
            this.threadsField.textboxKeyTyped(typedChar, keyCode);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.hostField.mouseClicked(mouseX, mouseY, mouseButton);
        this.minPortField.mouseClicked(mouseX, mouseY, mouseButton);
        this.maxPortField.mouseClicked(mouseX, mouseY, mouseButton);
        this.threadsField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void updateScreen() {
        this.hostField.updateCursorCounter();
        this.minPortField.updateCursorCounter();
        this.maxPortField.updateCursorCounter();
        this.threadsField.updateCursorCounter();
        super.updateScreen();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        this.running = false;
        super.onGuiClosed();
    }
}

