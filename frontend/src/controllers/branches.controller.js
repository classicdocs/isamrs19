import BranchesApiService from "@/api-services/branches.service";

export default {
  get(id){
    return BranchesApiService.get(id);
  }
};