package com.pharma.employee;

import com.google.gson.Gson;
import com.pharma.employee.config.JwtAuthenticationEntryPoint;
import com.pharma.employee.config.JwtTokenUtil;
import com.pharma.employee.config.WebSecurityConfiguration;
import com.pharma.employee.controllers.EmployeeController;
import com.pharma.employee.models.Employee;
import com.pharma.employee.models.Gender;
import com.pharma.employee.services.EmployeeService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=EmployeeApplication.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTests {

	@Autowired
	private MockMvc mvc;

	private final Gson gson = new Gson();

	@MockBean
	private EmployeeService employeeService;
	@Autowired
	private WebSecurityConfiguration webSecurityConfiguration;
	@MockBean
	private JwtTokenUtil jwtTokenUtil;
	@MockBean
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void contextLoads() {
		assertThat(employeeService).isNotNull();
		assertThat(webSecurityConfiguration).isNotNull();
		assertThat(jwtAuthenticationEntryPoint).isNotNull();
		assertThat(jwtTokenUtil).isNotNull();
	}

	@Test
	@WithMockUser(username = "admin", roles = "admin")
	public void getAllEmployeesAPI()
			throws Exception {
		Employee employee1 = new Employee(1, "Test First", "Test Last", "Test Middle", Gender.Male,
				new Date(), new ArrayList<>(), new ArrayList<>(), 1);
		Employee employee2 = new Employee(2, "Test First", "Test Last", "Test Middle", Gender.Male,
				new Date(), new ArrayList<>(), new ArrayList<>(), 2);
		employee1.setId(1);

		List<Employee> allEmployees = new ArrayList<>();
		allEmployees.add(employee1);
		allEmployees.add(employee2);

		given(employeeService.getAll()).willReturn(allEmployees);
		mvc.perform(MockMvcRequestBuilders
				.get("/employees/all")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
	}

	@Test
	@WithMockUser(username = "admin", roles = "admin")
	public void addEmployeeAPI()
			throws Exception {
		Employee employee = new Employee(1, "Test First", "Test Last", "Test Middle", Gender.Male,
				new Date(), new ArrayList<>(), new ArrayList<>(), 1);
		given(employeeService.add(any(Employee.class))).willReturn(employee);
		mvc.perform(MockMvcRequestBuilders
				.post("/employees/add")
				.content(gson.toJson(employee))
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
	}

	@Test
	@WithMockUser(username = "admin", roles = "admin")
	public void getEmployeeByIdAPI()
			throws Exception {
		Employee employee = new Employee(1, "Test First", "Test Last", "Test Middle", Gender.Male,
				new Date(), new ArrayList<>(), new ArrayList<>(), 1);
		String json = gson.toJson(employee);
		employee = gson.fromJson(json, Employee.class);
		given(employeeService.findbyId((long) 1)).willReturn(java.util.Optional.of(employee));
		mvc.perform(MockMvcRequestBuilders
				.get("/employees/1")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
	}

	@Test
	@WithMockUser(username = "admin", roles = "admin")
	public void getEmployeeByIdEmptyAPI()
			throws Exception {
		given(employeeService.findbyId((long) 1)).willReturn(java.util.Optional.empty());
		mvc.perform(MockMvcRequestBuilders
				.get("/employees/1")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().string("Employee not found"));
	}

	@Test
	@WithMockUser(username = "admin", roles = "admin")
	public void updateEmployeeAPI()
			throws Exception {
		Employee employee = new Employee(1, "Test First", "Test Last", "Test Middle", Gender.Male,
				new Date(), new ArrayList<>(), new ArrayList<>(), 1);
		given(employeeService.add(any(Employee.class))).willReturn(employee);
		mvc.perform(MockMvcRequestBuilders
				.put("/employees/update")
				.content(gson.toJson(employee))
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
	}

	@Test
	@WithMockUser(username = "admin", roles = "admin")
	public void deleteEmployeeAPI()
			throws Exception {
		mvc.perform(MockMvcRequestBuilders
				.delete("/employees/delete/1")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Employee deleted"));
	}
}
