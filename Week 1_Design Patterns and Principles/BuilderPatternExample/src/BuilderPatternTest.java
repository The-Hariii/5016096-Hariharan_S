public class BuilderPatternTest {
    public static void main(String[] args) {
        // Create different configurations of Computer using the Builder
        Computer gamingPC = new Computer.Builder()
            .setCPU("Intel i9")
            .setRAM("32GB")
            .setStorage("1TB SSD")
            .setGraphicsCard("NVIDIA RTX 3080")
            .setOperatingSystem("Windows 11")
            .build();

        Computer officePC = new Computer.Builder()
            .setCPU("Intel i5")
            .setRAM("16GB")
            .setStorage("512GB SSD")
            .setOperatingSystem("Windows 10")
            .build();

        // Print the configurations
        System.out.println("Gaming PC Configuration:");
        System.out.println("CPU: " + gamingPC.getCPU());
        System.out.println("RAM: " + gamingPC.getRAM());
        System.out.println("Storage: " + gamingPC.getStorage());
        System.out.println("Graphics Card: " + gamingPC.getGraphicsCard());
        System.out.println("Operating System: " + gamingPC.getOperatingSystem());

        System.out.println("\nOffice PC Configuration:");
        System.out.println("CPU: " + officePC.getCPU());
        System.out.println("RAM: " + officePC.getRAM());
        System.out.println("Storage: " + officePC.getStorage());
        System.out.println("Operating System: " + officePC.getOperatingSystem());
    }
}
