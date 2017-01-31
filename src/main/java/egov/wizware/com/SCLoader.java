package egov.wizware.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SCLoader extends ClassLoader {

    String[] _dirs;
    public SCLoader(String _path) {
        _dirs = _path.split(System.getProperty("path.separator"));
    }

    public SCLoader(String _path, ClassLoader _parent) {
        super(_parent);
        _dirs = _path.split(System.getProperty("path.separator"));
    }

    public void _extendClassPath(String _path) {
        String[] _exDirs = _path.split(System.getProperty("path.separator"));
        String[] _newDirs = new String[_dirs.length + _exDirs.length];
        System.arraycopy(_dirs, 0, _newDirs, 0, _dirs.length);
        System.arraycopy(_exDirs, 0, _newDirs, _dirs.length, _exDirs.length);
        _dirs = _newDirs;
    }

    public synchronized Class findClass(String _name) throws ClassNotFoundException
    {
        for (int i = 0; i < _dirs.length; i++)
        {
            System.out.println(_dirs[i] + "/" + _name.replace('.','/') + ".class" );
            byte[] _buf = _getClassData(_dirs[i], _name);
            if ( _buf != null )
                return defineClass(_name, _buf, 0, _buf.length);
        }

        throw new ClassNotFoundException();
    }

    private byte[] _getClassData(String _directory, String _name) {
        String _classFile = _directory + "/" + _name.replace('.','/') + ".class";
        int _classSize = (new Long(new File(_classFile).length())).intValue();
        byte[] _buf = new byte[_classSize];
        try {
            FileInputStream _fileIn = new FileInputStream(_classFile);
            _classSize = _fileIn.read(_buf);
            _fileIn.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return _buf;
    }

}
