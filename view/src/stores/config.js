import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useConfigStore = defineStore('config', ()=>{
    const showSidebar=ref(0);
    const username=ref('');
    const fnid = ref('')

return {showSidebar,username,fnid}
})
