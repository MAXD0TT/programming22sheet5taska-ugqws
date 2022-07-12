package edu.kit.informatik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    private static final String expressions1 =
            "1\n" +
            "(10+30i)+(90+20i)\n" +
            "(10+30i)-(90+20i)\n" +
            "(10+30i)*(90+20i)\n" +
            "(1+2i)/(10+5i)\n" +
            "(5+20i)/(10+5i)\n" +
            "[(10+30i)+(90+20i)]-(10+30i)\n" +
            "[(10+30i)+(90+20i)]-(10+30i)\n" +
            "(10+30i)+(10+30i)*(90+20i)\n" +
            "quit";
    private static final String expressions2 =
            "2\n" +
            "(R+Ri)\n" +
            "(R+4i)+(2+Ri)\n" +
            "(1+1i)/[(1+2i)/(10+5i)\n" +
            "(1+1i)/[(1+2i)/(10+5i)]\n" +
            "quit";

    private static final String testExpression9 = "(R+Ri)";
    private static final String testExpression10 = "(R+4i)+(2+Ri)";

    @Test
    void mainTest() {
        InputTe.mainTest(expressions1);
    }
}