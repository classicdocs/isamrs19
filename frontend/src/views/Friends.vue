<template>
  <div>
    <search-friends></search-friends>
    <v-container grid-list-xs>
      <v-layout row wrap>
        <v-flex lg6 md12 sm12 xs12>
          <friends 
          ></friends>
        </v-flex>
        <v-flex lg6 md12 sm12 xs12>
          <friend-requests
          ></friend-requests>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>

import SearchFriends from "@/components/Friends/SearchFriends.vue";
import ListOfFriends from "@/components/Friends/ListOfFriends.vue";
import FriendRequests from "@/components/Friends/FriendRequests.vue";

export default {
  name: 'Friends',
  components: {
    'search-friends' : SearchFriends,
    'friends' : ListOfFriends,
    'friend-requests' : FriendRequests,
  }
}
</script>
