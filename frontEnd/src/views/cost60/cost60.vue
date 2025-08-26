<template>
  <v-container fluid class="pa-4 ma-0 fill-height">
    <v-layout v-if="loading" align-center justify-center style="width: 100%">
      <v-progress-circular
        indeterminate
        color="primary"
        size="96"
        width="8"
      ></v-progress-circular
    ></v-layout>

    <v-layout v-else column style="width: 100%;">
      <div>
        <BarChart
          :chartData="barData"
          :options="barOptions"
          :width="600"
          :height="350"
        />
      </div>
    </v-layout>
  </v-container>
</template>

<script src="./cost60.js"></script>
<style src="./cost60.css"></style>
<link
  rel="stylesheet"
  href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,100,0,0"
/>
