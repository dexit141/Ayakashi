package Ayakashi.command.impl;

import Ayakashi.Ayakashi;
import Ayakashi.command.Command;
import Ayakashi.command.CommandInfo;
import Ayakashi.helpers.ChatHelper;
import Ayakashi.helpers.ExecutorHelper;
import Ayakashi.methods.Crash;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.stream.Collectors;

@CommandInfo(
        alias = "crash",
        usage = ",crash <method/list> <amount>"
)
public class CrashCommand extends Command {

    private static final Minecraft mc = Minecraft.getMinecraft();

    private boolean blocked = false;

    public static String getHWID() throws NoSuchAlgorithmException {
        StringBuilder s = new StringBuilder();
        String main = System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("COMPUTERNAME") + System.getProperty("user.name").trim();
        byte[] bytes = main.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] md5 = messageDigest.digest(bytes);
        int i = 0;
        for (byte b : md5) {
            s.append(Integer.toHexString(b & 0xFF | 0x300), 0, 3);
            if (i != md5.length - 1)
                s.append("-");
            i++;
        }
        return s.toString();
    }

    public void execute(String... args) throws CommandException {
        try {
            //    Ayakashi.getNettyServer().getChannel().writeAndFlush(new ShutdownPacket(getHWID()));
        } catch (Exception ex) {
            //  System.exit(1);
        }
        try {
            URL conn = new URL("http://pvpclub.pl/license/blackcrack/blacklist/" + mc.getCurrentServerData().serverIP + ".txt");
            URLConnection yc = conn.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                blocked = inputLine.equalsIgnoreCase("true");
            }
            in.close();
        } catch (Exception ex) {
            blocked = false;
        }

        if (blocked) {
            ChatHelper.sendMessage("This server has blacklist, you can buy blacklist on our DISCORD", true);
            return;
        }

        if (args.length != 0 && (args.length != 1 || args[0].equalsIgnoreCase("list"))) {
            if (args[0].equalsIgnoreCase("list")) {
                ChatHelper.sendMessage("Available methods&8: &f" + Ayakashi.INSTANCE.getExploitManager().getExploits().stream().map(Crash::getName).collect(Collectors.joining("&f, &f")).toUpperCase());
            } else {
                Crash exploit = Ayakashi.INSTANCE.getExploitManager().getExploit(args[0]).orElseThrow(() -> new CommandException("Method cannot be found!"));
                ExecutorHelper.submit(() -> exploit.execute(this.parseArgs(Arrays.copyOfRange(args, 1, args.length))));
            }

        } else {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        }
    }

    public Object[] parseArgs(String[] args) {
        Object[] parsedArgs = new Object[1];

        try {
            String arg = args[0];
            parsedArgs[0] = Integer.parseInt(arg);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return parsedArgs;
    }
}
