package JV20.isapsw.controller;

import JV20.isapsw.dto.PacijentDTO;
import JV20.isapsw.model.Pacijent;
import JV20.isapsw.service.PacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping(value = "api/pacijenti")
public class PacijentController {
    @Autowired
    private PacijentService pacijentService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<PacijentDTO> saveCourse(@RequestBody PacijentDTO pacijentDTO) throws ParseException {

        Pacijent pacijent = new Pacijent();
        pacijent.setIme(pacijentDTO.getIme());
        pacijent.setPrezime(pacijentDTO.getPrezime());
        pacijent.setEmail(pacijentDTO.getEmail());
        pacijent.setKorisnickoIme(pacijentDTO.getKorisnickoIme());
        pacijent.setLozinka(pacijentDTO.getLozinka());

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = df.parse(pacijentDTO.getDatumRodjenja());

        pacijent.setDatumRodjenja(date);
        pacijent.setDatumRegistrovanja(new Date());

        pacijent = pacijentService.save(pacijent);
        return new ResponseEntity<>(new PacijentDTO(pacijent), HttpStatus.CREATED);
    }

}