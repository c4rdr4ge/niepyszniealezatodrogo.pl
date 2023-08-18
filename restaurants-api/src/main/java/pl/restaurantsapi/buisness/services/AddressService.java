package pl.restaurantsapi.buisness.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.restaurantsapi.buisness.dto.AddressDTO;
import pl.restaurantsapi.buisness.dto.mappers.AddressMapper;
import pl.restaurantsapi.infrastructure.database.entities.AddressEntity;
import pl.restaurantsapi.infrastructure.database.repositories.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressService {

    AddressRepository addressRepository;
    AddressMapper addressMapper;

    @Transactional
    public AddressDTO getAddressById(Integer addressId) {
        Optional<AddressEntity> address = addressRepository.findById(addressId);
        if(address.isPresent()) {
            return addressMapper.map(address.get());
        } else {
            throw new EntityNotFoundException("Address with addressId: [%s], not found".formatted(addressId));
        }
    }

    @Transactional
    public List<AddressDTO> getAddressByAddressStreet(String addressStreet) {
        return addressRepository.findAll().stream()
                .filter(address -> address.getAddressStreet().contains(addressStreet))
                .map(address -> addressMapper.map(address))
                .toList();

    }

    @Transactional
    public void addNewAddress (AddressDTO addressDTO) {
        AddressEntity address = AddressEntity.builder()
                .addressCountry(addressDTO.getAddressCountry())
                .addressCity(addressDTO.getAddressCity())
                .addressPostalCode(addressDTO.getAddressPostalCode())
                .addressStreet(addressDTO.getAddressStreet())
                .build();

        addressRepository.save(address);
    }
}
