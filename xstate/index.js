'use strict';

import xstate from 'xstate';

const { Machine, interpret }  = xstate;

const promiseMachine = Machine({
    id: 'promise',
    initial: 'pending',
    states: {
        pending: {
            on: {
                RESOLVE: 'resolved',
                REJECT: 'rejected',
            }
        },
        resolved: {
            type: 'final',
        },
        rejected: {
            type: 'final'
        },
    },
});

const promiseService = interpret(promiseMachine).onTransition(state => console.log(state.value));

promiseService.start();

promiseService.send('RESOLVE');