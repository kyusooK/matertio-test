<template>
    <div>
        <Number label="SalesOrderNum" v-model="value.salesOrderNum" :editMode="editMode"/>
        <String label="SalesPerson" v-model="value.salesPerson" :editMode="editMode"/>
        <String label="Status" v-model="value.status" :editMode="editMode"/>
        <SalesType offline label="SalesType" v-model="value.salesType" :editMode="editMode" @change="change"/>
        <CompanyId offline label="CompanyId" v-model="value.companyId" :editMode="editMode" @change="change"/>
        <SalesItemDetailGrid label="SalesItems" offline v-model="value.salesItems" :editMode="editMode" @change="change"/>

        <v-divider class="border-opacity-100 my-divider"></v-divider>
        <v-layout row justify-end>
            <v-btn
                width="64px"
                color="primary"
                @click="save"
            >
                저장
            </v-btn>
        </v-layout>
    </div>
</template>


<script>

import BaseEntity from './base-ui/BaseEntity.vue'
import Number from './primitives/Number.vue'
import String from './primitives/String.vue'
import String from './primitives/String.vue'
import SalesItemDetailGrid from './ui/SalesItemDetailGrid.vue'

export default {
    name: 'SalesOrder',
    mixins:[BaseEntity],
    components:{
        Number,
        String,
        String,
        SalesType,
        CompanyId,
        SalesItemDetailGrid,
    },
    
    data: () => ({
        path: "salesOrders"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
