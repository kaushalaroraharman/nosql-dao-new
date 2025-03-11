/*
 *
 *
 *   *******************************************************************************
 *
 *     Copyright (c) 2023-24 Harman International
 *
 *
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *
 *     you may not use this file except in compliance with the License.
 *
 *     You may obtain a copy of the License at
 *
 *
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 *
 *     Unless required by applicable law or agreed to in writing, software
 *
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *     See the License for the specific language governing permissions and
 *
 *     limitations under the License.
 *
 *
 *
 *     SPDX-License-Identifier: Apache-2.0
 *
 *    *******************************************************************************
 *
 *
 */

package org.eclipse.ecsp.nosqldao;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods for adding, listing, and appending fields.
 */
public class Updates {

    /**
     * The Update ops.
     */
    private List<UpdateOp> updateOps = new ArrayList<>();

    /**
     * Adds a field set operation.
     *
     * @param field the field to set
     * @param val the value to set
     * @return the updated Updates object
     */
    public Updates addFieldSet(String field, Object val) {
        updateOps.add(new FieldSetOp(field, val));
        return this;
    }

    /**
     * Adds a field unset operation.
     *
     * @param field the field to unset
     * @return the updated Updates object
     */
    public Updates addFieldUnset(String field) {
        updateOps.add(new UnsetOp(field));
        return this;
    }

    /**
     * Adds a list append operation.
     *
     * @param field the field to append to
     * @param val the value to append
     * @return the updated Updates object
     */
    public Updates addListAppend(String field, Object val) {
        updateOps.add(new PushOp(field, val));
        return this;
    }

    /**
     * Adds a multi-value list append operation.
     *
     * @param field the field to append to
     * @param val the values to append
     * @return the updated Updates object
     */
    public Updates addListAppendMulti(String field, List<Object> val) {
        updateOps.add(new PushMultiOp(field, val));
        return this;
    }

    /**
     * Adds a set append operation.
     *
     * @param field the field to append to
     * @param val the value to append
     * @return the updated Updates object
     */
    public Updates addSetAppend(String field, Object val) {
        updateOps.add(new SetOp(field, val));
        return this;
    }

    /**
     * Adds a multi-value set append operation.
     *
     * @param field the field to append to
     * @param val the values to append
     * @return the updated Updates object
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Updates addSetAppendMulti(String field, List<?> val) {
        updateOps.add(new SetMultiOp(field, val));
        return this;
    }

    /**
     * Adds an increment operation.
     *
     * @param field the field to increment
     * @return the updated Updates object
     */
    public Updates addIncr(String field) {
        updateOps.add(new IncOp(field));
        return this;
    }

    /**
     * Adds an increment operation by a specified value.
     *
     * @param field the field to increment
     * @param by the value to increment by
     * @return the updated Updates object
     */
    public Updates addIncr(String field, long by) {
        updateOps.add(new IncOp(field, by));
        return this;
    }

    /**
     * Adds a decrement operation.
     *
     * @param field the field to decrement
     * @return the updated Updates object
     */
    public Updates addDecr(String field) {
        updateOps.add(new DecOp(field));
        return this;
    }

    /**
     * Adds a decrement operation by a specified value.
     *
     * @param field the field to decrement
     * @param by the value to decrement by
     * @return the updated Updates object
     */
    public Updates addDecr(String field, long by) {
        updateOps.add(new DecOp(field, by));
        return this;
    }

    /**
     * Adds a remove operation.
     *
     * @param field the field to remove from
     * @param val the value to remove
     * @return the updated Updates object
     */
    public Updates addRemoveOp(String field, Object val) {
        updateOps.add(new RemoveOp(field, val));
        return this;
    }

    /**
     * Traverse.
     *
     * @param v
     *         the v
     */
    public void traverse(UpdateOpVisitor v) {
        for (UpdateOp o : updateOps) {
            o.accept(v);
        }
    }

    /**
     * The interface Update op.
     */
    public static interface UpdateOp {

        /**
         * Accepts a visitor that implements the UpdateOpVisitor interface.
         *
         * @param v the visitor to accept
         */
        public void accept(UpdateOpVisitor v);

    }

    /**
     * The interface Update op visitor.
     */
    public static interface UpdateOpVisitor {

        /**
         * Visit a FieldSetOp operation.
         *
         * @param op the FieldSetOp operation to visit
         */
        public void visit(FieldSetOp op);

        /**
         * Visit an UnsetOp operation.
         *
         * @param op the UnsetOp operation to visit
         */
        public void visit(UnsetOp op);

        /**
         * Visit an PushOp operation.
         *
         * @param op the PushOp operation to visit
         */
        public void visit(PushOp op);

        /**
         * Visit an PushMultiOp operation.
         *
         * @param op the PushMultiOp operation to visit
         */
        public void visit(PushMultiOp op);

        /**
         * Visit an SetOp operation.
         *
         * @param op the SetOp operation to visit
         */
        public void visit(SetOp op);

        /**
         * Visit an SetMultiOp operation.
         *
         * @param op the SetMultiOp operation to visit
         */
        public void visit(@SuppressWarnings("rawtypes") SetMultiOp op);

        /**
         * Visit an IncOp operation.
         *
         * @param op the IncOp operation to visit
         */
        public void visit(IncOp op);

        /**
         * Visit an DecOp operation.
         *
         * @param op the DecOp operation to visit
         */
        public void visit(DecOp op);

        /**
         * Visit an RemoveOp operation.
         *
         * @param op the RemoveOp operation to visit
         */
        public void visit(RemoveOp op);
    }

    /**
     * The type Field op.
     */
    public abstract static class FieldOp implements UpdateOp {

        /**
         * The field.
         */
        private String field;

        /**
         * Instantiates a new field op.
         *
         * @param field the field
         */
        protected FieldOp(String field) {
            this.field = field;
        }

        /**
         * Gets field.
         *
         * @return the field
         */
        public String getField() {
            return field;
        }

        /**
         * Sets field.
         *
         * @param field the field
         */
        public void setField(String field) {
            this.field = field;
        }
    }

    /**
     * The type Push op.
     */
    public static class PushOp extends FieldOp {

        /**
         * The val.
         */
        private Object val;

        /**
         * Instantiates a new push op.
         *
         * @param field the field
         * @param val the val
         */
        public PushOp(String field, Object val) {
            super(field);
            this.val = val;
        }

        /**
         * Accept.
         *
         * @param v the v
         */
        @Override
        public void accept(UpdateOpVisitor v) {
            v.visit(this);
        }

        /**
         * Gets val.
         *
         * @return the val
         */
        public Object getVal() {
            return val;
        }

        /**
         * Sets val.
         *
         * @param val the val
         */
        public void setVal(Object val) {
            this.val = val;
        }

    }

    /**
     * The type Push multi op.
     */
    public static class PushMultiOp extends FieldOp {

        /**
         * The values.
         */
        private List<Object> values = new ArrayList<>();

        /**
         * Instantiates a new push multi op.
         *
         * @param field the field
         * @param val the val
         */
        public PushMultiOp(String field, List<Object> val) {
            super(field);
            this.values = val;
        }

        /**
         * Accept.
         *
         * @param v the v
         */
        @Override
        public void accept(UpdateOpVisitor v) {
            v.visit(this);
        }

        /**
         * Gets values.
         *
         * @return the values
         */
        public List<Object> getValues() {
            return values;
        }

        /**
         * Sets values.
         *
         * @param values the values
         */
        public void setValues(List<Object> values) {
            this.values = values;
        }

    }

    /**
     * The type Field set op.
     */
    public static class FieldSetOp extends FieldOp {

        /**
         * The val.
         */
        private Object val;

        /**
         * Instantiates a new field set op.
         *
         * @param field the field
         * @param val the val
         */
        public FieldSetOp(String field, Object val) {
            super(field);
            this.val = val;
        }

        /**
         * Accept.
         *
         * @param v the v
         */
        @Override
        public void accept(UpdateOpVisitor v) {
            v.visit(this);
        }

        /**
         * Gets val.
         *
         * @return the val
         */
        public Object getVal() {
            return val;
        }

        /**
         * Sets val.
         *
         * @param val the val
         */
        public void setVal(Object val) {
            this.val = val;
        }

    }

    /**
     * The type Unset op.
     */
    public static class UnsetOp extends FieldOp {

        /**
         * Instantiates a new unset op.
         *
         * @param field the field
         */
        public UnsetOp(String field) {
            super(field);
        }

        /**
         * Accept.
         *
         * @param v the v
         */
        @Override
        public void accept(UpdateOpVisitor v) {
            v.visit(this);
        }
    }

    /**
     * The type Set op.
     */
    public class SetOp extends FieldOp implements UpdateOp {

        /**
         * The val.
         */
        private Object val = null;

        /**
         * Instantiates a new set op.
         *
         * @param field the field
         * @param val the val
         */
        public SetOp(String field, Object val) {
            super(field);
            this.val = val;
        }

        /**
         * Accept.
         *
         * @param v the v
         */
        @Override
        public void accept(UpdateOpVisitor v) {
            v.visit(this);
        }

        /**
         * Gets val.
         *
         * @return the val
         */
        public Object getVal() {
            return val;
        }

        /**
         * Sets val.
         *
         * @param val the val
         */
        public void setVal(Object val) {
            this.val = val;
        }
    }

    /**
     * This class specify field and object to remove from collection.
     *
     * @author root
     */
    public class RemoveOp extends FieldOp implements UpdateOp {

        /**
         * The val.
         */
        private Object val = null;

        /**
         * Instantiates a new remove op.
         *
         * @param field the field
         * @param val the val
         */
        public RemoveOp(String field, Object val) {
            super(field);
            this.val = val;
        }

        /**
         * Accept.
         *
         * @param v the v
         */
        @Override
        public void accept(UpdateOpVisitor v) {
            v.visit(this);
        }

        /**
         * Gets val.
         *
         * @return the val
         */
        public Object getVal() {
            return val;
        }

        /**
         * Sets val.
         *
         * @param val the val
         */
        public void setVal(Object val) {
            this.val = val;
        }
    }

    /**
     * The type Set multi op.
     */
    public class SetMultiOp<T> extends FieldOp implements UpdateOp {

        /**
         * The values.
         */
        private List<T> values = null;

        /**
         * Instantiates a new set multi op.
         *
         * @param field the field
         * @param val the val
         */
        public SetMultiOp(String field, List<T> val) {
            super(field);
            this.values = val;
        }

        /**
         * Accept.
         *
         * @param v the v
         */
        @Override
        public void accept(UpdateOpVisitor v) {
            v.visit(this);
        }

        /**
         * Gets values.
         *
         * @return the values
         */
        public List<T> getValues() {
            return values;
        }

        /**
         * Sets values.
         *
         * @param values the values
         */
        public void setValues(List<T> values) {
            this.values = values;
        }

    }

    /**
     * The type Inc op.
     */
    public class IncOp extends FieldOp implements UpdateOp {

        /**
         * The by.
         */
        private long by = 1;

        /**
         * Instantiates a new inc op.
         *
         * @param field the field
         */
        public IncOp(String field) {
            super(field);
        }

        /**
         * Instantiates a new increment operation with a specified value.
         *
         * @param field the field to increment
         * @param by the value to increment by
         */
        public IncOp(String field, long by) {
            super(field);
            this.by = by;
        }

        /**
         * Accept.
         *
         * @param v : UpdateOpVisitor
         */
        @Override
        public void accept(UpdateOpVisitor v) {
            v.visit(this);
        }

        /**
         * Gets the by value.
         *
         * @return the value of by
         */
        public long getBy() {
            return by;
        }

        /**
         * Sets the by value.
         *
         * @param by the value to set by
         */
        public void setBy(long by) {
            this.by = by;
        }

    }

    /**
     * The type Dec op.
     */
    public class DecOp extends FieldOp implements UpdateOp {

        /**
         * The By.
         */
        private long by = 1;

        /**
         * Instantiates a new Dec op.
         *
         * @param field the field
         */
        public DecOp(String field) {
            super(field);
        }

        /**
         * Instantiates a new Dec op.
         *
         * @param field the field
         * @param by the by
         */
        public DecOp(String field, long by) {
            super(field);
            this.by = by;
        }

        /**
         * Accept.
         *
         * @param v the v
         */
        @Override
        public void accept(UpdateOpVisitor v) {
            v.visit(this);
        }

        /**
         * Gets by.
         *
         * @return the by
         */
        public long getBy() {
            return by;
        }

        /**
         * Sets by.
         *
         * @param by the by
         */
        public void setBy(long by) {
            this.by = by;
        }
    }

}