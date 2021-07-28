package simplex.equations;


import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class EquationElementsSeparatorTest {

    @Test
    public void should_separateConstant_whenLowerOrEqual() {

        String equation = "2x1 + 3x2 + 4x3 <= 5";
        EquationElementsSeparator separator = new EquationElementsSeparator();
        separator.separate(equation);

        assertEquals(separator.getConstant(), "5");
    }

    @Test
    public void should_separateConstant_whenEqual() {

        String equation = "2x1 + 3x2+ 4x3 = 5";
        EquationElementsSeparator separator = new EquationElementsSeparator();
        separator.separate(equation);

        assertEquals(separator.getConstant(), "5");
    }

    @Test
    public void should_separateConstant_whenHigherOrEqual() {

        String equation = "2x1 + 3x2+ 4x3 >= 5";
        EquationElementsSeparator separator = new EquationElementsSeparator();
        separator.separate(equation);

        assertEquals(separator.getConstant(), "5");
    }

    @Test
    public void should_separateConstant_whenMinusInConstant() {

        String equation = "2x1 + 3x2+ 4x3 >= -5";
        EquationElementsSeparator separator = new EquationElementsSeparator();
        separator.separate(equation);

        assertEquals(separator.getConstant(), "-5");
    }


    @Test
    public void should_separateVariable_whenLowerOrEqual() {

        String equation = "2x1 + 3x2+ 4x3 >= 5";
        EquationElementsSeparator separator = new EquationElementsSeparator();
        separator.separate(equation);
        HashMap<String, String> resultMap = separator.getCoefficientsPerNameMap();

        assertEquals(resultMap.get("x1"), "2");
        assertEquals(resultMap.get("x2"), "3");
        assertEquals(resultMap.get("x3"), "4");
    }


    @Test
    public void should_separateVariable_whenMinusInEquation() {

        String equation = "2x1 - 3x2 + 4x3 >= 5";

        EquationElementsSeparator separator = new EquationElementsSeparator();
        separator.separate(equation);
        HashMap<String, String> resultMap = separator.getCoefficientsPerNameMap();

        assertEquals(resultMap.get("x1"), "2");
        assertEquals(resultMap.get("x2"), "-3");
        assertEquals(resultMap.get("x3"), "4");
    }

    @Test
    public void should_separateVariable_whenMinusInEquationBeginning() {

        String equation = "- 2x1 - 3x2 + 4x3 >= 5";

        EquationElementsSeparator separator = new EquationElementsSeparator();
        separator.separate(equation);
        HashMap<String, String> resultMap = separator.getCoefficientsPerNameMap();

        assertEquals(resultMap.get("x1"), "-2");
        assertEquals(resultMap.get("x2"), "-3");
        assertEquals(resultMap.get("x3"), "4");
    }


    @Test
    public void should_separateVariable_whenOnlyVariableNames() {

        String equation = "-x1 - x2 + x3 >= 5";

        EquationElementsSeparator separator = new EquationElementsSeparator();
        separator.separate(equation);
        HashMap<String, String> resultMap = separator.getCoefficientsPerNameMap();

        assertEquals(resultMap.get("x1"), "-1");
        assertEquals(resultMap.get("x2"), "-1");
        assertEquals(resultMap.get("x3"), "1");
    }


    @Test
    public void should_separateVariable_whenOnlyOneVariable() {

        String equation = "x1 >= 0";

        EquationElementsSeparator separator = new EquationElementsSeparator();
        separator.separate(equation);
        HashMap<String, String> resultMap = separator.getCoefficientsPerNameMap();

        assertEquals(resultMap.get("x1"), "1");
    }


    @Test
    public void should_separateConstant_whenOnlyOneVariable() {

        String equation = "-x1 >= 0";
        EquationElementsSeparator separator = new EquationElementsSeparator();
        separator.separate(equation);

        assertEquals(separator.getConstant(), "0");
    }

    @Test
    public void should_separateObjective() {

        String equation = "x1 + 2x2 + 3x3 - x4";

        EquationElementsSeparator separator = new EquationElementsSeparator();
        separator.separate(equation);
        HashMap<String, String> resultMap = separator.getCoefficientsPerNameMap();


        assertEquals(resultMap.get("x1"), "1");
        assertEquals(resultMap.get("x2"), "2");
        assertEquals(resultMap.get("x3"), "3");
        assertEquals(resultMap.get("x4"), "-1");
    }


}