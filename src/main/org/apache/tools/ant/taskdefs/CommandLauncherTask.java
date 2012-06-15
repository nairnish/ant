package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.launcher.CommandLauncher;

public class CommandLauncherTask extends Task {
    private boolean vmLauncher;
    private CommandLauncher commandLauncher;

    public synchronized void addConfigured(CommandLauncher commandLauncher) {
        if (this.commandLauncher != null) {
            throw new BuildException("Only one CommandLauncher can be installed");
        }
        this.commandLauncher = commandLauncher;
    }

    @Override
    public void execute() {
        if (commandLauncher != null) {
            if (vmLauncher) {
                CommandLauncher.setVMLauncher(getProject(), commandLauncher);
            } else {
                CommandLauncher.setShellLauncher(getProject(), commandLauncher);
            }
        }
    }

    public void setVmLauncher(boolean vmLauncher) {
        this.vmLauncher = vmLauncher;
    }

}
