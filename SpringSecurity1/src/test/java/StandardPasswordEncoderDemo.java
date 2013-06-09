import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class StandardPasswordEncoderDemo {
    @Test
    public void testCannotReproduceHashFromConfigFile() {
        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        String password = "koala";
        String encoded = encoder.encode(password);
        String encodedFromXML = "4efe081594ce25ee4efd9f7067f7f678a347bccf2de201f3adf2a3eb544850b465b4e51cdc3fcdde";

        // What we generated is not what's in the XML
        assertTrue(encodedFromXML != encoded);

        // But both what's in the XML and what we generated match the password.
        assertTrue(encoder.matches(password, encoded));
        assertTrue(encoder.matches(password, encodedFromXML));

        System.out.println(encoded);
   }

    @Test
    public void testAnotherRunWillAlsoYieldDifferentHashes() {
        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        String password = "koala";
        String encoded = encoder.encode(password);
        String encoded2 = encoder.encode(password);
        assertTrue(encoded2 != encoded);

        System.out.println(encoded);
        System.out.println(encoded2);
    }
}
