import * as _ from "lodash";

const USER_ROLES = {
  SYSADMIN: "System Admin",
  USER: "User",
  AIRLINEADMIN: "Airline Company Admin",
  HOTELADMIN: "Hotel Admin",
  RENTACARADMIN: "RentACar Admin"
};

export class User {
  constructor(data) {
    _.assignWith(this, data);
  }

  static get USER_ROLES() {
    return USER_ROLES;
  }

  isSysAdmin() {
    return this.role === USER_ROLES.SYSADMIN;
  }

  isAirlineAdmin() {
    return this.role === USER_ROLES.AIRLINEADMIN;
  }

  isHotelAdmin() {
    return this.role === USER_ROLES.HOTELADMIN;
  }

  isRentacarAdmin() {
    return this.role === USER_ROLES.RENTACARADMIN;
  }

  isUser() {
    return this.role === USER_ROLES.USER;
  }
}
