import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class UserGUI extends GridTemplateGUI{
    private final String path;
    public UserGUI(String username) {
        super("User\\" + username + "\\");
        this.path = "User\\" + username + "\\";
        setTitle(username); // Override title back to simple name
        initUser();
    }

    @Override
    protected ActionListener getBackButtonActionListener() {
        return e -> {
            dispose();
            new MainGUI();
        };
    }

    @Override
    protected ActionListener getButtonActionListener(String path) {
        return e -> {
            dispose();
            new RoomGUI(path);
        };
    }

    @Override
    protected ActionListener getAddButtonActionListener() {
        return e -> {
            JDialog newRoomDialog = new JDialog(this, "Create Room:");
            newRoomDialog.setLayout( new FlowLayout() );
            newRoomDialog.add( new JLabel ("Create a New Room:"));
            JTextField roomName = new JTextField("", 20);
            newRoomDialog.add(roomName);
            newRoomDialog.add( new JLabel ("Description:"));
            JTextField description = new JTextField("", 20);
            newRoomDialog.add(description);
            JButton okButton = new JButton ("Save");
            okButton.addActionListener(l -> {
                // TODO: Test this
                // TODO: add functionality for or delete the dimensions
                new Room(roomName.getText(), path, description.getText());
                newRoomDialog.dispose();
                dispose();
                new UserGUI(path.substring(path.indexOf("\\") + 1).substring(0, path.substring(path.indexOf("\\") + 1).length() - 1));
            });
            newRoomDialog.add(okButton);
            newRoomDialog.setSize(400,200);
            newRoomDialog.setVisible(true);
            add(newRoomDialog);
        };
    }

    private void initUser() {
        File user = new File(path);
        user.mkdir();
    }
}