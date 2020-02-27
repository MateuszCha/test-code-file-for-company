export class FirmeInffo {

  private name: string;
  private adrres: string;
  private nb: string;
  private postCode: string;
  private nbPhone: string;
  private city: string;

  constructor() {
    this.adrres = 'ul. Cos';
    this.name = 'Gruby Bekon';
    this.nb = '143/12A';
    this.postCode = '534-153';
    this.nbPhone = '344-567-890';
    this.city = 'Wroclaw';
  }

  getName(): string {
    return this.name;
  }
  getAdrres(): string {
    return this.adrres;
  }
  getNb(): string {
    return this.nb;
  }
  getPOstCode(): string {
    return this.postCode;
  }
  getnbPhone(): string {
    return this.nbPhone;
  }
  getCity(): string {
    return this.city;
  }

}
