import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Gender} from '../shared/models/gender.enum';
import {ThirdPartyService} from '../shared/services/third-party/third-party.service';
import {PatientDto} from '../shared/dto/patient-dto';
import {PatientService} from '../shared/services/patient/patient.service';
import {Patient} from '../shared/models/patient';
import {LocationService} from '../shared/services/location/location.service';

@Component({
  selector: 'app-client-overview',
  templateUrl: './client-overview.component.html',
  styleUrls: ['./client-overview.component.css']
})
export class ClientOverviewComponent implements OnInit {

  id: number;
  firstName: string;
  lastName: string;
  middleName: string;
  gender: Gender;
  dateOfBirth: Date;
  phoneNumber: string;
  dossierInformation: string;
  streetName: string;
  houseNumber: string;
  zipCode: string;
  city: string;
  country: string;
  locationId: number;
  createdPatient: Patient;

  constructor(private router: Router, private thirdPartyService: ThirdPartyService,
              private patientService: PatientService, private route: ActivatedRoute,
              private locationService: LocationService) {
    this.route.params.subscribe(params => {
      this.id = params.id;
    });
    if (this.id != null) {
      this.patientService.getById(this.id).subscribe(
        data => {
          this.id = data.id;
          this.firstName = data.firstName;
          this.lastName = data.lastName;
          this.middleName = data.middleName;
          this.gender = data.gender;
          this.dateOfBirth = data.dateOfBirth;
          this.phoneNumber = data.phoneNumber;
          this.dossierInformation = data.dossier.information;
          this.locationId = data.locationId;
          if (this.locationId != null) {
            this.locationService.getById(this.locationId).subscribe(
              location => {
                this.streetName = location.street;
                this.houseNumber = location.houseNumber;
                this.zipCode = location.zipCode;
                this.city = location.city;
                this.country = location.country;
              },
              error => {
                console.log(error);
              });
          }
        });
    }
  }

  ngOnInit(): void {
  }

  navigateToHome(): void {
    this.router.navigate(['/home']);
  }

  generateRandomInformation(): void {
    this.thirdPartyService.getRandomAdress().subscribe(data => {
      const fullAddress: string = data[0];
      const splitAddress = fullAddress.split(', ');
      const street = splitAddress[0];
      const tempStreetName = (Number)(street.split(' ')[1].slice(0, 1));
      if (tempStreetName === Number(/[0-9]/)) {
        this.streetName = street.split(' ')[0] + ' ' + street.split(' ')[1];
        this.houseNumber = street.split(' ')[2];
      } else {
        this.streetName = street.split(' ')[0];
        this.houseNumber = street.split(' ')[1];
      }
      this.zipCode = splitAddress[2];
      this.city = splitAddress[3];
      this.country = 'Nederland';
    });
    this.thirdPartyService.getRandomName().subscribe(name => {
      this.firstName = name[0].split(' ')[0];
      this.lastName = name[0].split(' ')[1];
    });
    const day: number = this.thirdPartyService.getRandomDay();
    const month: number = this.thirdPartyService.getRandomMonth();
    const year: number = this.thirdPartyService.getRandomYear();
    if (day !== null && month !== null && year !== null) {

      this.dateOfBirth = new Date();
      this.dateOfBirth.setFullYear(year, month, day);
    }
    this.thirdPartyService.getRandomPhoneNumber().subscribe(phoneNumber => {
      this.phoneNumber = phoneNumber[0];
    });
    this.thirdPartyService.getRandomDossierInformation().subscribe(dossierInformation => {
      this.dossierInformation = '';
      const allDossiers = dossierInformation.split('<br>');
      for (const dossier of allDossiers) {
        this.dossierInformation += dossier.toString().substring(0, 250);
      }
    });
    const genderNumber = this.thirdPartyService.getRandomGender();
    if (genderNumber === 0) {
      this.gender = Gender.Man;
    } else if (genderNumber === 1) {
      this.gender = Gender.Vrouw;
    } else {
      this.gender = Gender.Anders;
    }
  }

  savePatientInformation(): void {
    const patientDto: PatientDto = new PatientDto();
    patientDto.firstName = this.firstName;
    patientDto.lastName = this.lastName;
    if (this.middleName !== undefined) {
      patientDto.middleName = this.middleName;
    } else {
      patientDto.middleName = '';
    }
    patientDto.gender = this.gender;
    patientDto.dateOfBirth = this.dateOfBirth;
    patientDto.phoneNumber = this.phoneNumber;
    patientDto.dossierInformation = this.dossierInformation;
    patientDto.streetName = this.streetName;
    patientDto.houseNumber = this.houseNumber;
    patientDto.zipCode = this.zipCode;
    patientDto.city = this.city;
    patientDto.country = this.country;
    console.log(patientDto);

    this.patientService.createPatient(patientDto).subscribe(patient => {
      this.createdPatient = patient;
    });
  }
}
