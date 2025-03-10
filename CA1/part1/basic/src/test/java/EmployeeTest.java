import com.greglturnquist.payroll.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void createEmployee () {

        //act
        Employee employee1 = new Employee("João", "Oliveira","Engenheiro Informático", "joaooliveira@isep.pt",  2);

        //assert
        assertNotNull(employee1);
    }

    @Test
    public void testEmptyConstructor() {
        Employee emp = new Employee();

        assertNotNull(emp);
        assertNull(emp.getId());
        assertNull(emp.getFirstName());
        assertNull(emp.getLastName());
        assertNull(emp.getDescription());
        assertNull(emp.getJobYears());
    }

    @Test
    public void testEquals_SameObject() {
        Employee emp1 = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt",  5);
        assertTrue(emp1.equals(emp1)); // Deve retornar true (comparação com o próprio objeto)
    }

    @Test
    public void testEquals_EqualObjects() {
        Employee emp1 = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt", 5);
        Employee emp2 = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt",5);
        assertTrue(emp1.equals(emp2)); // Deve retornar true (dados iguais)
    }

    @Test
    public void testEquals_DifferentObjects() {
        Employee emp1 = new Employee("John", "Doe", "Developer","joaooliveira@isep.pt",  5);
        Employee emp2 = new Employee("Jane", "Smith", "Manager", "joaooliveira@isep.pt", 10);
        assertFalse(emp1.equals(emp2)); // Deve retornar false (dados diferentes)
    }

    @Test
    public void testEquals_DifferentId() {
        Employee emp1 = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt", 5);
        Employee emp2 = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt",  5);
        emp1.setId(1L);
        emp2.setId(2L);
        assertFalse(emp1.equals(emp2)); // Deve retornar false (IDs diferentes)
    }

    @Test
    public void testEquals_NullObject() {
        Employee emp1 = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt",  5);
        assertFalse(emp1.equals(null)); // Deve retornar false (comparação com null)
    }

    @Test
    public void testEquals_DifferentClass() {
        Employee emp1 = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt",   5);
        String notAnEmployee = "Not an employee";
        assertFalse(emp1.equals(notAnEmployee)); // Deve retornar false (objetos de classes diferentes)
    }

    @Test
    public void testEquals_DifferentJobYears() {
        Employee emp1 = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt", 5);
        Employee emp2 = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt", 10);
        assertFalse(emp1.equals(emp2)); // Deve retornar false (jobYears diferentes)
    }

    @Test
    public void testHashCode_EqualObjects() {
        Employee emp1 = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt",  5);
        Employee emp2 = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt",  5);

        assertEquals(emp1.hashCode(), emp2.hashCode()); // Se equals() for true, hashCode() deve ser igual
    }

    @Test
    public void testHashCode_DifferentObjects() {
        Employee emp1 = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt",  5);
        Employee emp2 = new Employee("Jane", "Smith", "Manager", "joaooliveira@isep.pt",  10);

        assertNotEquals(emp1.hashCode(), emp2.hashCode());
    }

    @Test
    public void testGettersAndSetters() {
        Employee emp = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt",  5);

        emp.setId(100L);
        emp.setFirstName("Jane");
        emp.setLastName("Smith");
        emp.setDescription("Manager");
        emp.setJobYears(10);

        assertEquals(100L, emp.getId());
        assertEquals("Jane", emp.getFirstName());
        assertEquals("Smith", emp.getLastName());
        assertEquals("Manager", emp.getDescription());
        assertEquals(10, emp.getJobYears());
    }

    @Test
    public void testSetJobYears_ValidValue() {
        Employee emp = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt",  5);
        emp.setJobYears(10);

        assertEquals(10, emp.getJobYears());
    }


    @Test
    public void testIsParametersInvalid_BlankValue() {
        Employee emp = new Employee();
        assertTrue(emp.isParametersInvalid("   "));
    }

    @Test
    public void testIsParametersInvalid_ValidValue() {
        Employee emp = new Employee();
        assertFalse(emp.isParametersInvalid("Valid"));
    }

    @Test
    public void testIsJobYearsInvalid_NullValue() {
        Employee emp = new Employee();
        assertTrue(emp.isjobYearsInvalid(null));
    }

    @Test
    public void testIsJobYearsInvalid_NegativeValue() {
        Employee emp = new Employee();
        assertTrue(emp.isjobYearsInvalid(-1));
    }

    @Test
    public void testIsJobYearsInvalid_ValidValue() {
        Employee emp = new Employee();
        assertFalse(emp.isjobYearsInvalid(5));
    }


    @Test
    public void testSetJobYears_InvalidValue() {
        Employee emp = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt",  5);
        emp.setJobYears(-5);

        assertEquals(-5, emp.getJobYears());
    }

    @Test
    public void testSetters_InvalidValues() {
        Employee emp = new Employee("John", "Doe", "Developer", "joaooliveira@isep.pt",  5);

        emp.setFirstName(null);
        emp.setLastName(" ");
        emp.setDescription("");

        assertNull(emp.getFirstName());
        assertEquals(" ", emp.getLastName());
        assertEquals("", emp.getDescription());
    }





}