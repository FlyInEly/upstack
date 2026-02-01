package flyinely.mcm.upstack.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {

   public static final ModConfigSpec SPEC;
   public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

   public static final ModConfigSpec.BooleanValue TEST;
   public static final ModConfigSpec.BooleanValue TEST2;

   static {
      BUILDER.push("cat");
      TEST = BUILDER.define("test", false);
      TEST2 = BUILDER.define("test2", true);
      BUILDER.pop();
      SPEC = BUILDER.build();
   }

}
