package ovh.wiktormalyska.pharmacysystembackend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ovh.wiktormalyska.pharmacysystembackend.pharmacist.*;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.Pharmacy;
import ovh.wiktormalyska.pharmacysystembackend.pharmacy.PharmacyServiceImpl;

public class PharmacistServiceImplTest {

  @Mock private PharmacistRepository pharmacistRepository;

  @Mock private PharmacyServiceImpl pharmacyServiceImpl;

  @InjectMocks private PharmacistServiceImpl pharmacistService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    when(pharmacyServiceImpl.getPharmacyById(1L)).thenReturn(Pharmacy.builder().id(1L).build());
  }

  @Test
  void testAddNewPharmacist() {
    Pharmacist pharmacist =
        Pharmacist.builder().id(1L).name("John").surname("Doe").isActive(true).build();
    PharmacistRequestDTO pharmacistRequestDTO =
        PharmacistRequestDTO.builder().id(1L).name("John").surname("Doe").pharmacyId(1L).build();

    when(pharmacistRepository.save(any(Pharmacist.class))).thenReturn(pharmacist);

    PharmacistResponseDTO response = pharmacistService.addNewPharmacist(pharmacistRequestDTO);

    assertNotNull(response);
    assertEquals("John", response.getName());
  }

  @Test
  void testGetPharmacistByIdByIDDtoById() {
    Pharmacy pharmacy = Pharmacy.builder().id(1L).build();
    Pharmacist pharmacist =
        Pharmacist.builder()
            .id(1L)
            .name("John")
            .surname("Doe")
            .isActive(true)
            .pharmacy(pharmacy)
            .build();

    when(pharmacistRepository.findById(pharmacist.getId())).thenReturn(Optional.of(pharmacist));

    PharmacistResponseDTO response = pharmacistService.getPharmacistDtoById(pharmacist.getId());

    assertNotNull(response);
    assertEquals("John", response.getName());
  }

  @Test
  void testGetPharmacistByIdByIDDtoById_NotFound() {
    Pharmacist pharmacist =
        Pharmacist.builder().id(1L).name("John").surname("Doe").isActive(true).build();

    when(pharmacistRepository.findById(pharmacist.getId())).thenReturn(Optional.empty());

    ResponseStatusException exception =
        assertThrows(
            ResponseStatusException.class,
            () -> pharmacistService.getPharmacistDtoById(pharmacist.getId()));

    assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    assertEquals("Pharmacist with this id doesn't exist.", exception.getReason());
  }

  @Test
  void testUpdatePharmacist() {
    Pharmacy pharmacy = Pharmacy.builder().id(1L).build();
    Pharmacist pharmacist =
        Pharmacist.builder()
            .id(1L)
            .name("John")
            .surname("Doe")
            .isActive(true)
            .pharmacy(pharmacy)
            .build();
    Pharmacist updatedPharmacist =
        Pharmacist.builder()
            .id(1L)
            .name("John")
            .surname("Smith")
            .isActive(true)
            .pharmacy(pharmacy)
            .build();
    PharmacistRequestDTO updatedPharmacistRequestDTO =
        PharmacistRequestDTO.builder().id(1L).name("John").surname("Smith").pharmacyId(1L).build();

    when(pharmacistRepository.findById(updatedPharmacistRequestDTO.getId()))
        .thenReturn(Optional.of(pharmacist));
    when(pharmacistRepository.save(any(Pharmacist.class))).thenReturn(updatedPharmacist);
    when(pharmacyServiceImpl.getPharmacyById(1L)).thenReturn(pharmacy);

    PharmacistResponseDTO response =
        pharmacistService.updatePharmacist(updatedPharmacistRequestDTO);

    assertNotNull(response);
    assertEquals("John", response.getName());
    assertEquals("Smith", response.getSurname());
  }

  @Test
  void testRemovePharmacistById() {
    Pharmacy pharmacy = Pharmacy.builder().id(1L).build();
    Pharmacist pharmacist =
        Pharmacist.builder()
            .id(1L)
            .name("John")
            .surname("Doe")
            .isActive(true)
            .pharmacy(pharmacy)
            .build();
    Pharmacist updatedPharmacist =
        Pharmacist.builder()
            .id(1L)
            .name("John")
            .surname("Doe")
            .isActive(false)
            .pharmacy(pharmacy)
            .build();
    when(pharmacistRepository.findById(pharmacist.getId())).thenReturn(Optional.of(pharmacist));
    when(pharmacistRepository.save(any(Pharmacist.class))).thenReturn(updatedPharmacist);

    pharmacistService.removePharmacistById(pharmacist.getId());
    ResponseStatusException exception =
        assertThrows(
            ResponseStatusException.class,
            () -> pharmacistService.getPharmacistDtoById(pharmacist.getId()));

    assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    assertEquals("Pharmacist has been deleted.", exception.getReason());
  }

  @Test
  void testRemovePharmacistById_AlreadyDeleted() {
    Pharmacist pharmacist =
        Pharmacist.builder().id(1L).name("John").surname("Doe").isActive(false).build();
    when(pharmacistRepository.findById(pharmacist.getId())).thenReturn(Optional.of(pharmacist));

    ResponseStatusException exception =
        assertThrows(
            ResponseStatusException.class,
            () -> pharmacistService.removePharmacistById(pharmacist.getId()));

    assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    assertEquals("Pharmacist has been deleted.", exception.getReason());
  }
}