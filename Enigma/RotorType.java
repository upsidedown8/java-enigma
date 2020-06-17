package CipherSolver.Enigma;

public enum RotorType {
    RotorI{
        public String toString(){
            return "Rotor I";
        }
        public String getValue(){
            return "EKMFLGDQVZNTOWYHXUSPAIBRCJ.QQ";
        }
    },
    RotorII{
        public String toString(){
            return "Rotor II";
        }
        public String getValue(){
            return "AJDKSIRUXBLHWTMCQGZNPYFVOE.EE";
        }
    },
    RotorIII{
        public String toString(){
            return "Rotor III";
        }
        public String getValue(){
            return "BDFHJLCPRTXVZNYEIWGAKMUSQO.VV";
        }
    },
    RotorIV{
        public String toString(){
            return "Rotor IV";
        }
        public String getValue(){
            return "ESOVPZJAYQUIRHXLNFTGKDCMWB.JJ";
        }
    },
    RotorV{
        public String toString(){
            return "Rotor V";
        }
        public String getValue(){
            return "VZBRGITYUPSDNHLXAWMJQOFECK.ZZ";
        }
    },
    RotorVI{
        public String toString(){
            return "Rotor VI";
        }
        public String getValue(){
            return "JPGVOUMFYQBENHZRDKASXLICTW.ZM";
        }
    },
    RotorVII{
        public String toString(){
            return "Rotor VII";
        }
        public String getValue(){
            return "NZJHGRCXMYSWBOUFAIVLPEKQDT.ZM";
        }
    },
    RotorVIII{
        public String toString(){
            return "Rotor VIII";
        }
        public String getValue(){
            return "FKQHTLXOCBJSPDZRAMEWNIUYGV.ZM";
        }
    };

    public abstract String toString();
    public abstract String getValue();
}
