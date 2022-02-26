<template>
  <v-app>

      <component v-bind:is="layout"></component>
  </v-app>
</template>

<script>
import BaseLayout from './layout/Base'

export default {
  name: 'App',

  data: () => ({

  }),
  computed: {
    layout(){
      return this.$store.state.layout;
    }
  },
  components: {
    'baseLayout': BaseLayout,
  }
};
</script>
