<template>
    <v-snackbar
        v-model="snackbar.status"
        :timeout="snackbar.timeout"
        :color="snackbar.color"
    >
        
        <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
            Close
        </v-btn>
    </v-snackbar>
    <v-col :cols="2">
      <DrawerContent/>
    </v-col>
    <v-col :cols="10">
        <v-row>
            <!-- <UserProfile style="right: 2px;"/> -->
        </v-row>
        <div style="max-height:80vh; margin-top: 90px;">
            <div class="panel">
                <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                    <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                        <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                    </v-btn>
                    <v-btn style="margin-left: 5px;" @click="editSelectedRow()" class="contrast-primary-text" small color="primary">
                        <v-icon small>mdi-pencil</v-icon>수정
                    </v-btn>
                    <v-btn style="margin-left: 5px;" @click="produceDialog = true" class="contrast-primary-text" small color="primary" >
                        <v-icon small>mdi-minus-circle-outline</v-icon>produce
                    </v-btn>
                    <v-dialog v-model="produceDialog" width="500">
                        <ProduceCommand
                            @closeDialog="produceDialog = false"
                            @produce="produce"
                        ></ProduceCommand>
                    </v-dialog>
                </div>
                <div class="mb-5 text-lg font-bold"></div>
                <div class="table-responsive">
                    <v-table>
                        <thead>
                            <tr>
                            <th>Id</th>
                            <th>SalesOrderNum</th>
                            <th>SalesPerson</th>
                            <th>Status</th>
                            <th>SalesType</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(val, idx) in value" :key="val" @click="changeSelectedRow(val)" :style="val === selectedRow ? 'background-color: #f0f3ff;':''">
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="SalesOrderNum">{{ val.salesOrderNum }}</td>
                            <td class="whitespace-nowrap" label="SalesPerson">{{ val.salesPerson }}</td>
                            <td class="whitespace-nowrap" label="Status">{{ val.status }}</td>
                            <td class="whitespace-nowrap" label="SalesType">{{ val.salesType }}</td>
                            <SalesItemDetailGrid label="SalesItems" offline v-if="selectedRow" v-model="selectedRow.salesItems" :selectedRow="selectedRow"/>
                            <Icon style="margin-top: 15px;" icon="mi:delete" @click="deleteRow(val)" />
                            </tr>
                        </tbody>
                    </v-table>
                </div>
            </div>
            <v-col>
                <v-dialog
                    v-model="openDialog"
                    transition="dialog-bottom-transition"
                    width="35%"
                >
                    <v-card>
                        <v-toolbar
                            color="primary"
                            class="elevation-0"
                            height="50px"
                        >
                            <div style="color:white; font-size:17px; font-weight:700;">Company 등록</div>
                            <v-spacer></v-spacer>
                            <v-icon
                                color="white"
                                small
                                @click="closeDialog()"
                            >mdi-close</v-icon>
                        </v-toolbar>
                        <v-card-text>
                            <SalesOrder :offline="offline"
                                :isNew="!value.idx"
                                :editMode="true"
                                :inList="false"
                                v-model="newValue"
                                @add="append"
                            />
                        </v-card-text>
                    </v-card>
                </v-dialog>
            </v-col>
            <v-col style="margin-bottom:40px;">
                <div class="text-center">
                    <v-dialog
                        width="332.5"
                        fullscreen
                        hide-overlay
                        transition="dialog-bottom-transition"
                    >
                        <v-btn
                            style="position:absolute; top:2%; right:2%"
                            @click="closeDialog()"
                            depressed
                            icon 
                            absolute
                        >
                            <v-icon>mdi-close</v-icon>
                        </v-btn>
                    </v-dialog>
                </div>
            </v-col>
        </div>
    </v-col>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import DrawerContent from '../../../layouts/components/DrawerContent.vue';
import BaseGrid from '../base-ui/BaseGrid.vue'

import SalesOrder from '../SalesOrder.vue'
import SalesItemDetailGrid from './SalesItemDetailGrid.vue'


export default {
    name: 'salesOrderGrid',
    mixins:[BaseGrid],
    components:{
        DrawerContent,
        SalesOrder,
        SalesItemDetailGrid,
    },
    data: () => ({
        path: 'salesOrders',
        produceDialog: false,
    }),
    watch: {
    },
    methods:{
        produce(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "produce", params)
                this.$EventBus.$emit('show-success','produce 성공적으로 처리되었습니다.')
                this.produceDialog = false
            }catch(e){
                console.log(e)
            }
            
        },
    }
}

</script>