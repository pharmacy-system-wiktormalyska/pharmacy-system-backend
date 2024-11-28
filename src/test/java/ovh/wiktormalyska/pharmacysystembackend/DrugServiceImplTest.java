package ovh.wiktormalyska.pharmacysystembackend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.drug.*;

public class DrugServiceImplTest {

  @Mock private DrugRepository drugRepository;

  @InjectMocks private DrugServiceImpl drugServiceImpl;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAddNewDrug_Success() {
    DrugRequestDTO requestDTO = DrugRequestDTO.builder().name("Test Drug").build();

    Drug drug = Drug.builder().id(1L).name("Test Drug").build();

    DrugResponseDTO responseDTO = DrugResponseDTO.builder().id(1L).name("Test Drug").build();

    when(drugRepository.save(any(Drug.class))).thenReturn(drug);
    DrugResponseDTO result = drugServiceImpl.addNewDrug(requestDTO);

    assertNotNull(result);
    assertEquals(responseDTO.getId(), result.getId());
    assertEquals(responseDTO.getName(), result.getName());
  }

  @Test
  public void testAddNewDrug_Failure() {
    DrugRequestDTO requestDTO = DrugRequestDTO.builder().name("Test Drug").build();

    when(drugRepository.save(any(Drug.class))).thenThrow(new RuntimeException("Error saving drug"));

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> drugServiceImpl.addNewDrug(requestDTO));
    assertEquals("Error saving drug", exception.getMessage());
  }

  @Test
  public void testGetDrugDtoByName_Success() {
    String name = "Test Drug";
    Drug drug = Drug.builder().id(1L).name(name).build();

    DrugResponseDTO responseDTO = DrugResponseDTO.builder().id(1L).name(name).build();

    when(drugRepository.findByName(name)).thenReturn(Optional.of(drug));
    DrugResponseDTO result = drugServiceImpl.getDrugDtoByName(name);

    assertNotNull(result);
    assertEquals(responseDTO.getId(), result.getId());
    assertEquals(responseDTO.getName(), result.getName());
  }

  @Test
  public void testGetDrugDtoByName_NotFound() {
    String name = "Test Drug";

    when(drugRepository.findByName(name)).thenReturn(Optional.empty());

    ResponseStatusException exception =
        assertThrows(ResponseStatusException.class, () -> drugServiceImpl.getDrugDtoByName(name));
    assertEquals("404 NOT_FOUND \"Drug with this id doesn't exist.\"", exception.getMessage());
  }

  @Test
  public void testGetDrugDtoById_Success() {
    Long id = 1L;
    Drug drug = Drug.builder().id(id).name("Test Drug").build();

    DrugResponseDTO responseDTO = DrugResponseDTO.builder().id(id).name("Test Drug").build();

    when(drugRepository.findById(id)).thenReturn(Optional.of(drug));
    DrugResponseDTO result = drugServiceImpl.getDrugDtoById(id);

    assertNotNull(result);
    assertEquals(responseDTO.getId(), result.getId());
    assertEquals(responseDTO.getName(), result.getName());
  }

  @Test
  public void testGetDrugDtoById_NotFound() {
    Long id = 1L;

    when(drugRepository.findById(id)).thenReturn(Optional.empty());

    ResponseStatusException exception =
        assertThrows(ResponseStatusException.class, () -> drugServiceImpl.getDrugDtoById(id));
    assertEquals("404 NOT_FOUND \"Drug with this name doesn't exist.\"", exception.getMessage());
  }

  @Test
public void testUpdateDrug_Success() {
  Long id = 1L;
  DrugRequestDTO requestDTO = DrugRequestDTO.builder().id(id).name("Updated Drug").build();

  Drug drug = Drug.builder().id(id).name("Old Drug").build();
  Drug updatedDrug = Drug.builder().id(id).name("Updated Drug").build();
  DrugResponseDTO responseDTO = DrugResponseDTO.builder().id(id).name("Updated Drug").build();

  when(drugRepository.findById(id)).thenReturn(Optional.of(drug));
  when(drugRepository.save(any(Drug.class))).thenReturn(updatedDrug);
  DrugResponseDTO result = drugServiceImpl.updateDrug(requestDTO);

  assertNotNull(result);
  assertEquals(responseDTO.getId(), result.getId());
  assertEquals(responseDTO.getName(), result.getName());
}

  @Test
  public void testRemoveDrugByName_Success() {
    String name = "Test Drug";
    Drug drug = Drug.builder().id(1L).name(name).build();

    DrugResponseDTO responseDTO = DrugResponseDTO.builder().id(1L).name(name).build();

    when(drugRepository.findByName(name)).thenReturn(Optional.of(drug));
    when(drugRepository.save(any(Drug.class))).thenReturn(drug); // Add this line
    DrugResponseDTO result = drugServiceImpl.removeDrugByName(name);

    assertNotNull(result);
    assertEquals(responseDTO.getId(), result.getId());
    assertEquals(responseDTO.getName(), result.getName());
  }

  @Test
  public void testRemoveDrugByName_NotFound() {
    String name = "Test Drug";

    when(drugRepository.findByName(name)).thenReturn(Optional.empty());

    ResponseStatusException exception =
        assertThrows(ResponseStatusException.class, () -> drugServiceImpl.removeDrugByName(name));
    assertEquals("404 NOT_FOUND \"Drug with this id doesn't exist.\"", exception.getMessage());
  }

  @Test
  public void testRemoveDrugById_Success() {
    Long id = 1L;
    Drug drug = Drug.builder().id(id).name("Test Drug").build();

    DrugResponseDTO responseDTO = DrugResponseDTO.builder().id(id).name("Test Drug").build();

    when(drugRepository.findById(id)).thenReturn(Optional.of(drug));
    when(drugRepository.save(any(Drug.class))).thenReturn(drug); // Add this line
    DrugResponseDTO result = drugServiceImpl.removeDrugById(id);

    assertNotNull(result);
    assertEquals(responseDTO.getId(), result.getId());
    assertEquals(responseDTO.getName(), result.getName());
  }

  @Test
  public void testRemoveDrugById_NotFound() {
    Long id = 1L;

    when(drugRepository.findById(id)).thenReturn(Optional.empty());

    ResponseStatusException exception =
        assertThrows(ResponseStatusException.class, () -> drugServiceImpl.removeDrugById(id));
    assertEquals("404 NOT_FOUND \"Drug with this name doesn't exist.\"", exception.getMessage());
  }
}
