/*
 * NOTES
 * toBe for comparing values, toEqual when comparing objects
 * 
*/


describe("calculator", function(){

    beforeEach(function(){
        Calculator.currentVal = Calculator.varAfterEachExample;
        jasmine.addMatchers({
            customMatcher: function(){
                return {
                    compare: function(actual, expected){
                        var result = {};
                        result.pass = true;
                        result.message = "custom matcher has passed";
                        return result;
                    }
                };
            }
        });
    });

    // initial state test case
    it("should retain the current value of all time", function(){
        expect(Calculator.currentVal).toBeDefined();
        expect(Calculator.currentVal).toEqual(0);
        expect(Calculator.currentVal).customMatcher(0);
    });

    // not.toEqual
    it("initial value should not be any other value than zero", function(){
        expect(Calculator.currentVal).not.toEqual(1);
    });

    // testing toBe
    it("initial value should match the value as well as the type", function(){
        expect(Calculator.currentVal).toBe(0);
    });

    it("should add numbers", function(){
        expect(Calculator.add(5)).toEqual(5);
        expect(Calculator.add(5)).toEqual(10);
    });

    //spying on functions
    it("should call add function", function(){
        // adding spies
        spyOn(Calculator, "add").and.callThrough();

        Calculator.add(5);
        expect(Calculator.add).toHaveBeenCalled();
        expect(Calculator.currentVal).toEqual(5);
    });

    it("should add any number of numbers", function(){
        expect(Calculator.addAny(1,2,3)).toEqual(6);
    });
});