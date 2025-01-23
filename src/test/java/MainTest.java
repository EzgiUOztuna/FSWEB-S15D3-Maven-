import org.example.Main;
import org.example.WordCounter;
import org.example.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ResultAnalyzer.class)
public class MainTest {

    List<Employee> employees = new LinkedList<>();

    @BeforeEach
    void setUp() {
        employees.add(new Employee(1, "Dogancan", "Kinik"));
        employees.add(new Employee(1, "Dogancan", "Kinik"));
        employees.add(new Employee(2, "Seyyit Battal", "Arvas"));
        employees.add(new Employee(2, "Seyyit Battal", "Arvas"));
        employees.add(new Employee(3, "Anil", "Ensari"));
        employees.add(new Employee(3, "Anil", "Ensari"));
        employees.add(new Employee(4, "Burak", "Cevizli"));
        employees.add(null);

    }

    @DisplayName("Employee sınıfı doğru access modifiers sahip mi")
    @Test
    public void testTaskAccessModifiers() throws NoSuchFieldException {
        Field idFields = employees.get(0).getClass().getDeclaredField("id");
        Field firstnameFields = employees.get(0).getClass().getDeclaredField("firstname");
        Field lastnameFields = employees.get(0).getClass().getDeclaredField("lastname");

        assertEquals(idFields.getModifiers(), 2);
        assertEquals(firstnameFields.getModifiers(), 2);
        assertEquals(lastnameFields.getModifiers(), 2);
    }

    @DisplayName("findDuplicates method doğru çalışıyor mu?")
    @Test
    public void testFindDuplicatesMethod() {
        List<Employee> list = Main.findDuplicates(employees);
        assertEquals(list.size(), 3);
        assertEquals(list.get(0).getFirstname(), "Dogancan");
    }

    @DisplayName("findUniques method doğru çalışıyor mu?")
    @Test
    public void testFindUniquesMethod() {
        Map<Integer, Employee> map = Main.findUniques(employees);
        assertEquals(map.size(), 4);
        assertEquals(map.get(1).getFirstname(), "Dogancan");
    }

    @DisplayName("removeDuplicates method doğru çalışıyor mu?")
    @Test
    public void testRemoveMethod() {
        List<Employee> list = Main.removeDuplicates(employees);
        System.out.println(list);
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getFirstname(), "Burak");
    }

    @DisplayName("calculatedWord method doğru çalışıyor mu?")
    @Test
    public void testCalculateWordMethod() {
        Map<String, Integer> map = WordCounter.calculatedWord();
        assertEquals(map.get("which"), 3);
        assertEquals(map.get("turkish"), 2);
        assertEquals(map.get("mustafa"), 3);
        assertEquals(map.get("kemal"), 3);
    }

}
