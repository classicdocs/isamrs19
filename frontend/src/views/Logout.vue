<template>
  
</template>

<script>

import LoginController from '@/controllers/login.controller.js';

export default {
  name: "Logout",
  beforeCreate() {
    LoginController.logout();
  }
}
</script>

<style>

</style>
