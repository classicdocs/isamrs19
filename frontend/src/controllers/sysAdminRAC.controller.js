import sysAdminRACApiService from "@/api-services/sysAdminRAC.service";

export default {
  create(data) {
    return sysAdminRACApiService.create(data);
  }
};