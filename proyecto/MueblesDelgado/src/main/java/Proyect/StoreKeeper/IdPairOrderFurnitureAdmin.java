package Proyect.StoreKeeper;

import Proyect.Repositories.IdPairOrderFurnitureRepository;
import Proyect.Validations.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdPairOrderFurnitureAdmin {
    private final IdPairOrderFurnitureRepository idPairOrderFurnitureRepository;

    @Autowired
    public IdPairOrderFurnitureAdmin(IdPairOrderFurnitureRepository idPairOrderFurnitureRepository){this.idPairOrderFurnitureRepository = idPairOrderFurnitureRepository}

    public IdPairOrderFurniture findByOrderId(Long p_orderFurnitureId) {
        return idPairOrderFurnitureRepository.findById(p_orderFurnitureId)
                .orElseThrow(() -> new IllegalArgumentException("OrderFurnitureID not found: ID " + p_orderFurnitureId));
    }

    public List<IdPairOrderFurniture> getAllOrders() {
        return idPairOrderFurnitureRepository.findAll();
    }

    public IdPairOrderFurniture addOrder(IdPairOrderFurniture p_idPairOrderFurniture) {
        ValidationUtils.validateNonNull(p_idPairOrderFurniture, "OrderFurnitureID");
        return idPairOrderFurnitureRepository.save(p_idPairOrderFurniture);
    }

    public void setIdPairOrderFurnitures (List<IdPairOrderFurniture> idPairOrderFurnitures) {
        idPairOrderFurnitureRepository.saveAll(idPairOrderFurnitures);
    }

    public void removeIdPairOrderFurniture (Long p_orderFurnitureID) {
        if (idPairOrderFurnitureRepository.existsById( p_orderFurnitureID)) {
            idPairOrderFurnitureRepository.deleteById( p_orderFurnitureID);
        } else {
            throw new IllegalArgumentException("IdPairOrderFurniture not found: ID " + p_orderFurnitureID);
        }
    }

    public IdPairOrderFurniture updateIdPairOrderFurniture(IdPairOrderFurniture p_idPairOrderFurniture) {
        ValidationUtils.validateNonNull(p_idPairOrderFurniture, "idPairOrderFurniture");
        idPairOrderFurnitureRepository.findById(p_idPairOrderFurniture.getOrderID())
                .orElseThrow(() -> new IllegalArgumentException("Order not found: ID " + p_idPairOrderFurniture.getOrderID()));
        return idPairOrderFurnitureRepository.save(p_idPairOrderFurniture);
    }
}
