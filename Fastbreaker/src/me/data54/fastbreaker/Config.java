package me.data54.fastbreaker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Properties;

public class Config
{
  public static String getProperty(String property)
  {
    String mainDirectory = "plugins/Fastbreaker";
    Properties prop = new Properties();
    try {
      if (!new File("plugins/Fastbreaker/Config.txt").exists())
      {
        new File("plugins/Fastbreaker").mkdir();
        new File("plugins/Fastbreaker/Config.txt").createNewFile();
        PrintWriter out = new PrintWriter(new FileWriter("plugins/Fastbreaker/Config.txt"));
        out.println("#This is the Fastbreaker config file.");
        out.println("");
        out.println("#Breakable Blocks:");
        out.println("Redstone=true");
        out.println("Fences=true");
        out.println("Stairs=true");
        out.println("Obsidian=false");
        out.println("OPBedrock=false");
        out.println("#If you don't use permissions, add Usernames that are allowed to use fastbreaker:");
        out.println("User1=true");
        out.println("User2=true");
        out.println("User3=true");
        out.close();
      }

      FileInputStream in = new FileInputStream("plugins/Fastbreaker/Config.txt");
      prop.load(in);

      if (property.equals(property))
      {
        String WN = prop.getProperty(property);
        in.close();
        return WN;
      }
      else{
      in.close();}
    } catch (Exception e) {
      System.out.println("[Fastbreaker] Error while reading config.txt!");
      e.printStackTrace();
    }
    return "[Fastbreaker] Error while reading config.txt!";
  }
}