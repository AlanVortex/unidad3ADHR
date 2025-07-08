package utez.edu.mx.unidad3.modules.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.unidad3.utils.APIResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public APIResponse findAll() {
        List<Client> list = clientRepository.findAll();
        return new APIResponse("Operacion exitosa", list, false, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public APIResponse findById(Long id) {
        try {
            Client found = clientRepository.findById(id).orElse(null);
            if (found == null) {
                return new APIResponse("No se encontro al cliente solicitado", true, HttpStatus.NOT_FOUND);
            }
            return new APIResponse("Operacion exitosa", found, false, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new APIResponse("No se pudo consultar al cliente", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public APIResponse save(Client payload) {
        try {
            clientRepository.save(payload);
            return new APIResponse("Operacion exitosa", false, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new APIResponse("No se pudo consultar al cliente", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public APIResponse update(Client payload) {
        try {
            if (clientRepository.findById(payload.getId()).isEmpty()) {
                return new APIResponse("Cliente no encontrado", true, HttpStatus.NOT_FOUND);
            }
            clientRepository.save(payload);
            return new APIResponse("Operacion exitosa", false, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new APIResponse("No se pudo actualizar al cliente", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public APIResponse remove(Client payload) {
        try {
            if (clientRepository.findById(payload.getId()).isEmpty()) {
                return new APIResponse("Cliente no encontrado", true, HttpStatus.NOT_FOUND);
            }
            clientRepository.deleteById(payload.getId());
            return new APIResponse("Operacion exitosa", false, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new APIResponse("No se pudo borrar el cliente", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public APIResponse update() {
        return null;
    }

    @Transactional(readOnly = true)
    public APIResponse remove() {
        return null;
    }
}
