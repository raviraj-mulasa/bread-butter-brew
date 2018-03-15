package net.geekscore.problems;

import java.nio.file.Path;
import java.nio.file.Paths;

class Shell {

    private Path pwdPath; // Present Working Dir absolute path

    Shell()  {
        this.pwdPath = Paths.get(System.getProperty("user.dir")).getRoot().toAbsolutePath();
    }

    Shell cd(final String path) {
        if(path == null) {
            throw new IllegalArgumentException("Not a valid path");
        }
        if(path.length() > 0) {
            /*
            Change directory from the root if path starts with root
            else change directory relative to pwd
            */
            final String rootDir   = this.pwdPath.getRoot().toString();
            final String startHere = path.startsWith(rootDir)  ? rootDir : this.path();
            this.pwdPath = this.normalizedPath(Paths.get(startHere, path)).toAbsolutePath();
        }
        return this;
    }

    public String path() {
        return this.pwdPath.toString();
    }

    private Path normalizedPath(final Path path) {
        return path.normalize(); /* Handles '.' , '..' , extra '/' */
    }
}

public class Directory {

    public static void main(String[] args) {

        final Shell shell = new Shell();
        assert shell.path().equals("/");

        shell.cd("/");
        assert shell.path().equals("/");

        shell.cd("usr/..");
        assert shell.path().equals("/");

        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        assert shell.path().equals("/usr/local");

        shell.cd("..");
        assert shell.path().equals("/usr");

        shell.cd("//lib///");
        assert shell.path().equals("/lib");

        shell.cd(null);

    }
}
