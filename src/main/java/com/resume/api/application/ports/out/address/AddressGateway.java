package com.resume.api.application.ports.out.address;

import com.resume.api.application.domains.entities.Address;
import java.util.List;
import java.util.Optional;

public interface AddressGateway {

  Address createAddress(Address address);

  Address updateAddress(Address address);

  Boolean deleteAddress(Address address);

  Optional<Address> getAddress(Long id);

  List<Address> getAllAddresses();
}
