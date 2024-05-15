import com.example.hospitalmanagementsystem.repository.DepartmentRepository;
import com.example.hospitalmanagementsystem.service.DepartmentManagementService;
import com.example.hospitalmanagementsystem.service.impl.DepartmentManagementServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class DepartmentsUnitTest {


    private DepartmentManagementService departmentManagementService;

    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        departmentRepository = mock(DepartmentRepository.class);
        departmentManagementService = new DepartmentManagementServiceImpl(departmentRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveDepartment() {
    }
}