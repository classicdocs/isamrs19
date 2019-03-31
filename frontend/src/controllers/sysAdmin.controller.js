import sysAdminApiService from "@/api-services/sysAdmin.service";

export default {
  create(data) {
    return sysAdminApiService.create(data);
  }
};