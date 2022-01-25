import Card from 'src/components/Cards/Card.vue'

export default {
  name: 'src-pages-repairing',
  components: {
    Card
  },
  props: [],
  data () {
        return {
          user: {
            company: 'Light dashboard',
            username: 'michael23',
            email: '',
            firstName: 'Mike',
            lastName: 'Andrew',
            address: 'Melbourne, Australia',
            city: 'melbourne',
            country: 'Australia',
            postalCode: '',
            aboutMe: `Lamborghini Mercy, Your chick she so thirsty, I'm in that two seat Lambo.`
          }
        }
      },
  computed: {

  },
  mounted () {

  },
  methods: {
    updateProfile () {
      alert('Your data: ' + JSON.stringify(this.user))
    }
  }
}


