import com.greglturnquist.payroll.Employee;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeTest {

    @Test
    void createEmployee () {

        //act
        Employee employee1 = new Employee("João", "Oliveira","Engenheiro Informático", 2);

        //assert
        assertNotNull(employee1);
    }

}