package CipherSolver.Enigma;

public enum ReflectorType {
    ReflectorA{
        public String toString(){
            return "Reflector A";
        }
        public String getValue(){
            return "EJMZALYXVBWFCRQUONTSPIKHGD";
        }
    },
    ReflectorB{
        public String toString(){
            return "Reflector B";
        }
        public String getValue(){
            return "YRUHQSLDPXNGOKMIEBFZCWVJAT";
        }
    },
    ReflectorC{
        public String toString(){
            return "Reflector C";
        }
        public String getValue(){
            return "FVPJIAOYEDRZXWGCTKUQSBNMHL";
        }
    };

    public abstract String toString();
    public abstract String getValue();
}
